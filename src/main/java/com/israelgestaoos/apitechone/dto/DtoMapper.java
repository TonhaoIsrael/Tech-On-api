package com.israelgestaoos.apitechone.dto;

import com.israelgestaoos.apitechone.dto.cliente.ClienteResponse;
import com.israelgestaoos.apitechone.dto.os.OrdemServicoResponse;
import com.israelgestaoos.apitechone.dto.usuario.UsuarioResponse;
import com.israelgestaoos.apitechone.model.Cliente;
import com.israelgestaoos.apitechone.model.OrdemServico;
import com.israelgestaoos.apitechone.model.Usuario;
import org.springframework.stereotype.Component;

@Component
public class DtoMapper {

    // ============================
    // CLIENTE → CLIENTE RESPONSE
    // ============================
    public ClienteResponse toClienteResponse(Cliente c) {
        if (c == null) return null;

        ClienteResponse dto = new ClienteResponse();
        dto.setId(c.getId());
        dto.setNome(c.getNome());
        dto.setTelefone(c.getTelefone());
        dto.setEmail(c.getEmail());
        dto.setEndereco(c.getEndereco());
        dto.setAtivo(c.isAtivo());
        return dto;
    }

    // ============================
    // USUARIO → USUARIO RESPONSE
    // ============================
    public UsuarioResponse toUsuarioResponse(Usuario u) {
        if (u == null) return null;

        UsuarioResponse dto = new UsuarioResponse();
        dto.setId(u.getId());
        dto.setNome(u.getNome());
        dto.setEmail(u.getEmail());
        dto.setRole(u.getRole().getNome());
        dto.setAtivo(u.isAtivo());
        return dto;
    }

    // ============================
    // ORDEM SERVICO → OS RESPONSE
    // ============================
    public OrdemServicoResponse toOrdemServicoResponse(OrdemServico os) {
        if (os == null) return null;

        OrdemServicoResponse dto = new OrdemServicoResponse();

        dto.setId(os.getId());
        dto.setTitulo(os.getTitulo());
        dto.setDescricao(os.getDescricao());

        // Cliente
        if (os.getCliente() != null) {
            dto.setClienteId(os.getCliente().getId());
            dto.setClienteNome(os.getCliente().getNome());
        }

        // Técnico
        if (os.getTecnico() != null) {
            dto.setTecnicoId(os.getTecnico().getId());
            dto.setTecnicoNome(os.getTecnico().getNome());
        }

        dto.setPrioridade(os.getPrioridade());
        dto.setStatus(os.getStatus());

        dto.setCriadoEm(os.getCriadoEm());
        dto.setDataAgendada(os.getDataAgendada());
        dto.setAgendada(os.isAgendada());

        return dto;
    }

}
