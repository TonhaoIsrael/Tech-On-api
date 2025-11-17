package com.israelgestaoos.apitechone.service;

import com.israelgestaoos.apitechone.dto.CriarOSRequest;
import com.israelgestaoos.apitechone.dto.AtualizarOSRequest;
import com.israelgestaoos.apitechone.model.OrdemServico;
import com.israelgestaoos.apitechone.model.Usuario;
import com.israelgestaoos.apitechone.repository.OrdemServicoRepository;
import com.israelgestaoos.apitechone.repository.ClienteRepository;
import com.israelgestaoos.apitechone.repository.UsuarioRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class OrdemServicoService {

    private final OrdemServicoRepository osRepository;
    private final ClienteRepository clienteRepository;
    private final UsuarioRepository usuarioRepository;

    public OrdemServicoService(
            OrdemServicoRepository osRepository,
            ClienteRepository clienteRepository,
            UsuarioRepository usuarioRepository
    ) {
        this.osRepository = osRepository;
        this.clienteRepository = clienteRepository;
        this.usuarioRepository = usuarioRepository;
    }

    // =============================== CRIAR OS ===============================
    public OrdemServico criar(CriarOSRequest req) {

        OrdemServico os = new OrdemServico();

        // >>> FALTAVA ISSO <<<
        os.setTitulo(req.getTitulo());

        os.setDescricao(req.getDescricao());
        os.setCliente(clienteRepository.findById(req.getClienteId())
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado")));

        os.setTecnico(usuarioRepository.findById(req.getTecnicoId())
                .orElseThrow(() -> new RuntimeException("Técnico não encontrado")));

        // ENUMS vindo do JSON
        os.setPrioridade(req.getPrioridade());
        os.setStatus(req.getStatus());

        // Agendamento opcional
        os.setDataAgendada(req.getDataAgendada());
        os.setAgendada(req.getDataAgendada() != null);

        return osRepository.save(os);
    }

    // =============================== LISTAR ===============================
    public List<OrdemServico> listar() {
        return osRepository.findAll();
    }

    // =============================== BUSCAR ===============================
    public OrdemServico buscar(Long id) {
        return osRepository.findById(id).orElse(null);
    }

    // =============================== ATUALIZAR ===============================
    public OrdemServico atualizar(Long id, AtualizarOSRequest req) {
        OrdemServico os = buscar(id);
        if (os == null) return null;

        os.setDescricao(req.getDescricao());
        os.setStatus(req.getStatus());
        os.setPrioridade(req.getPrioridade());

        return osRepository.save(os);
    }

    // =============================== ATRIBUIR TÉCNICO ===============================
    public OrdemServico atribuirTecnico(Long osId, Long tecnicoId) {

        OrdemServico os = buscar(osId);

        Usuario tecnico = usuarioRepository.findById(tecnicoId)
                .orElseThrow(() -> new RuntimeException("Técnico não encontrado"));

        os.setTecnico(tecnico);
        return osRepository.save(os);
    }
}
