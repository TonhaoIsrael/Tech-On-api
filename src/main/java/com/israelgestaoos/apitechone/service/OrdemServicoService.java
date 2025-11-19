package com.israelgestaoos.apitechone.service;

import com.israelgestaoos.apitechone.auth.AuthService;
import com.israelgestaoos.apitechone.dto.os.CriarOSRequest;
import com.israelgestaoos.apitechone.dto.os.AtualizarOSRequest;
import com.israelgestaoos.apitechone.model.OrdemServico;
import com.israelgestaoos.apitechone.model.Usuario;
import com.israelgestaoos.apitechone.model.enums.StatusOS;
import com.israelgestaoos.apitechone.repository.OrdemServicoRepository;
import com.israelgestaoos.apitechone.repository.ClienteRepository;
import com.israelgestaoos.apitechone.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;

@Service
public class OrdemServicoService {

    private final OrdemServicoRepository osRepository;
    private final ClienteRepository clienteRepository;
    private final UsuarioRepository usuarioRepository;
    private final AuthService authService;

    public OrdemServicoService(
            OrdemServicoRepository osRepository,
            ClienteRepository clienteRepository,
            UsuarioRepository usuarioRepository,
            AuthService authService
    ) {
        this.osRepository = osRepository;
        this.clienteRepository = clienteRepository;
        this.usuarioRepository = usuarioRepository;
        this.authService = authService;
    }

    // =============================== CRIAR OS (ADMIN) ===============================
    public OrdemServico criar(CriarOSRequest req) {

        Usuario usuario = authService.getUsuarioLogado();

        String roleNome = usuario.getRole().getNome();
        if (roleNome == null || !roleNome.toUpperCase().contains("ADMIN")) {
            throw new RuntimeException("Apenas ADMIN pode criar OS");
        }

        OrdemServico os = new OrdemServico();

        os.setTitulo(req.getTitulo());
        os.setDescricao(req.getDescricao());

        // CLIENTE OBRIGATÓRIO
        os.setCliente(clienteRepository.findById(req.getClienteId())
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado")));

        // TÉCNICO OPCIONAL
        if (req.getTecnicoId() != null) {
            Usuario tecnico = usuarioRepository.findById(req.getTecnicoId())
                    .orElseThrow(() -> new RuntimeException("Técnico não encontrado"));
            os.setTecnico(tecnico);
        }

        os.setPrioridade(req.getPrioridade());
        os.setStatus(req.getStatus());
        os.setDataAgendada(req.getDataAgendada());
        os.setAgendada(req.getDataAgendada() != null);

        return osRepository.save(os);
    }

    // =============================== LISTAR (ADMIN = tudo, TÉCNICO = só dele) ===============================
    public List<OrdemServico> listar() {

        Usuario usuario = authService.getUsuarioLogado();

        String roleNome = usuario.getRole().getNome();
        if (roleNome != null && roleNome.toUpperCase().contains("ADMIN")) {
            return osRepository.findAll();
        }

        return osRepository.findByTecnicoId(usuario.getId());
    }

    // =============================== BUSCAR (ADMIN = tudo, TÉCNICO = só dele) ===============================
    public OrdemServico buscar(Long id) {

        Usuario usuario = authService.getUsuarioLogado();

        OrdemServico os = osRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("OS não encontrada"));

        String roleNome = usuario.getRole().getNome();
        if (roleNome != null && roleNome.toUpperCase().contains("TECNICO")) {
            if (os.getTecnico() == null || !os.getTecnico().getId().equals(usuario.getId())) {
                throw new RuntimeException("Técnico não pode acessar OS de outro técnico");
            }
        }

        return os;
    }

    // =============================== ATUALIZAR ===============================
    public OrdemServico atualizar(Long id, AtualizarOSRequest req) {

        Usuario usuario = authService.getUsuarioLogado();
        OrdemServico os = buscar(id); // já verifica permissão de acesso

        String roleNome = usuario.getRole().getNome();

        // TÉCNICO: só altera OS dele
        if (roleNome != null && roleNome.toUpperCase().contains("TECNICO")) {

            if (os.getTecnico() == null || !os.getTecnico().getId().equals(usuario.getId())) {
                throw new RuntimeException("Técnico não pode editar OS de outro técnico");
            }

            if (req.getDescricao() != null) os.setDescricao(req.getDescricao());
            if (req.getPrioridade() != null) os.setPrioridade(req.getPrioridade());
            if (req.getStatus() != null) os.setStatus(req.getStatus());

            return osRepository.save(os);
        }

        // ADMIN: pode alterar tudo (aqui estamos mantendo só os mesmos campos)
        if (req.getDescricao() != null) os.setDescricao(req.getDescricao());
        if (req.getPrioridade() != null) os.setPrioridade(req.getPrioridade());
        if (req.getStatus() != null) os.setStatus(req.getStatus());

        return osRepository.save(os);
    }

    // =============================== ATRIBUIR TÉCNICO (ADMIN) ===============================
    public OrdemServico atribuirTecnico(Long osId, Long tecnicoId) {

        Usuario usuario = authService.getUsuarioLogado();

        String roleNome = usuario.getRole().getNome();
        if (roleNome == null || !roleNome.toUpperCase().contains("ADMIN")) {
            throw new RuntimeException("Apenas ADMIN pode atribuir técnico");
        }

        OrdemServico os = buscar(osId);

        Usuario tecnico = usuarioRepository.findById(tecnicoId)
                .orElseThrow(() -> new RuntimeException("Técnico não encontrado"));

        os.setTecnico(tecnico);
        return osRepository.save(os);
    }

    // =============================== LISTAR OS DO DIA ===============================
    public List<OrdemServico> listarDoDia(LocalDate dia) {

        // Usa a regra geral de listar (ADMIN = tudo, TÉCNICO = só dele)
        List<OrdemServico> base = listar();

        return base.stream()
                .filter(os -> os.getDataAgendada() != null
                        && os.getDataAgendada().toLocalDate().equals(dia))
                .sorted(
                        Comparator
                                .comparingInt((OrdemServico os) -> prioridadeStatus(os.getStatus()))
                                .thenComparing(OrdemServico::getDataAgendada)
                )
                .toList();
    }

    private int prioridadeStatus(StatusOS status) {
        return switch (status) {
            case ABERTO -> 0;
            case EM_ANDAMENTO -> 1;
            case CONCLUIDO -> 2;
            case CANCELADO -> 3;
        };
    }
}
