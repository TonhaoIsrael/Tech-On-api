package com.israelgestaoos.apitechone.service;

import com.israelgestaoos.apitechone.dto.ClienteRequest;
import com.israelgestaoos.apitechone.model.Cliente;
import com.israelgestaoos.apitechone.repository.ClienteRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public Cliente criar(ClienteRequest req) {
        Cliente c = new Cliente();
        c.setNome(req.getNome());
        c.setTelefone(req.getTelefone());
        c.setEmail(req.getEmail());
        c.setEndereco(req.getEndereco());
        return clienteRepository.save(c);
    }

    public List<Cliente> listar() {
        return clienteRepository.findAll();
    }

    public Cliente buscar(Long id) {
        return clienteRepository.findById(id).orElse(null);
    }

    public Cliente atualizar(Long id, ClienteRequest req) {
        Cliente c = buscar(id);
        if (c == null) return null;

        c.setNome(req.getNome());
        c.setTelefone(req.getTelefone());
        c.setEmail(req.getEmail());
        c.setEndereco(req.getEndereco());
        return clienteRepository.save(c);
    }

    public void deletar(Long id) {
        clienteRepository.deleteById(id);
    }
}
