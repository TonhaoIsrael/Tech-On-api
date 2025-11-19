package com.israelgestaoos.apitechone.controller;

import com.israelgestaoos.apitechone.dto.cliente.ClienteRequest;
import com.israelgestaoos.apitechone.dto.cliente.ClienteResponse;
import com.israelgestaoos.apitechone.dto.DtoMapper;
import com.israelgestaoos.apitechone.model.Cliente;
import com.israelgestaoos.apitechone.service.ClienteService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clientes")
@CrossOrigin(origins = "*")
public class ClienteController {

    private final ClienteService clienteService;
    private final DtoMapper mapper;

    public ClienteController(ClienteService clienteService, DtoMapper mapper) {
        this.clienteService = clienteService;
        this.mapper = mapper;
    }

    @PostMapping
    public ClienteResponse criar(@RequestBody ClienteRequest req) {
        Cliente c = clienteService.criar(req);
        return mapper.toClienteResponse(c);
    }

    @GetMapping
    public List<ClienteResponse> listar() {
        return clienteService.listar().stream()
                .map(mapper::toClienteResponse)
                .toList();
    }

    @GetMapping("/{id}")
    public ClienteResponse buscar(@PathVariable Long id) {
        Cliente c = clienteService.buscar(id);
        return mapper.toClienteResponse(c);
    }

    @PutMapping("/{id}")
    public ClienteResponse atualizar(@PathVariable Long id,
                                     @RequestBody ClienteRequest req) {
        Cliente c = clienteService.atualizar(id, req);
        return mapper.toClienteResponse(c);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        clienteService.deletar(id);
    }
}
