package com.israelgestaoos.apitechone.controller;

import com.israelgestaoos.apitechone.dto.CriarOSRequest;
import com.israelgestaoos.apitechone.dto.AtualizarOSRequest;
import com.israelgestaoos.apitechone.model.OrdemServico;
import com.israelgestaoos.apitechone.service.OrdemServicoService;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/os")
@CrossOrigin(origins = "*")
public class OrdemServicoController {

    private final OrdemServicoService ordemServicoService;

    public OrdemServicoController(OrdemServicoService ordemServicoService) {
        this.ordemServicoService = ordemServicoService;
    }

    @PostMapping
    public OrdemServico criar(@RequestBody CriarOSRequest req) {
        return ordemServicoService.criar(req);
    }

    @GetMapping
    public List<OrdemServico> listar() {
        return ordemServicoService.listar();
    }

    @GetMapping("/{id}")
    public OrdemServico buscar(@PathVariable Long id) {
        return ordemServicoService.buscar(id);
    }

    @PutMapping("/{id}")
    public OrdemServico atualizar(@PathVariable Long id, @RequestBody AtualizarOSRequest req) {
        return ordemServicoService.atualizar(id, req);
    }

    @PutMapping("/{id}/atribuir/{tecnicoId}")
    public OrdemServico atribuirTecnico(@PathVariable Long id, @PathVariable Long tecnicoId) {
        return ordemServicoService.atribuirTecnico(id, tecnicoId);
    }

}
