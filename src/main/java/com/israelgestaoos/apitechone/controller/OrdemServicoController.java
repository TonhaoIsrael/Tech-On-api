package com.israelgestaoos.apitechone.controller;

import com.israelgestaoos.apitechone.model.OrdemServico;
import com.israelgestaoos.apitechone.service.OrdemServicoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ordens-servico")
@CrossOrigin(origins = "*")
public class OrdemServicoController {

    private final OrdemServicoService ordemServicoService;

    public OrdemServicoController(OrdemServicoService ordemServicoService) {
        this.ordemServicoService = ordemServicoService;
    }

    @GetMapping
    public List<OrdemServico> listar() {
        return ordemServicoService.listarTodas();
    }

    @GetMapping("/{id}")
    public OrdemServico buscarPorId(@PathVariable Long id) {
        return ordemServicoService.buscarPorId(id).orElse(null);
    }

    @PostMapping
    public OrdemServico criar(@RequestBody OrdemServico ordem) {
        return ordemServicoService.salvar(ordem);
    }

    @PutMapping("/{id}")
    public OrdemServico atualizar(@PathVariable Long id, @RequestBody OrdemServico ordem) {
        ordem.setId(id);
        return ordemServicoService.salvar(ordem);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        ordemServicoService.deletar(id);
    }
}
