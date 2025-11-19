package com.israelgestaoos.apitechone.controller;

import com.israelgestaoos.apitechone.dto.DtoMapper;
import com.israelgestaoos.apitechone.dto.os.AtualizarOSRequest;
import com.israelgestaoos.apitechone.dto.os.CriarOSRequest;
import com.israelgestaoos.apitechone.dto.os.OrdemServicoResponse;
import com.israelgestaoos.apitechone.model.OrdemServico;
import com.israelgestaoos.apitechone.service.OrdemServicoService;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;

import java.util.List;

@RestController
@RequestMapping("/api/os")
@CrossOrigin(origins = "*")
public class OrdemServicoController {

    private final OrdemServicoService ordemServicoService;
    private final DtoMapper mapper;

    public OrdemServicoController(OrdemServicoService ordemServicoService, DtoMapper mapper) {
        this.ordemServicoService = ordemServicoService;
        this.mapper = mapper;
    }

    // ====================== CRIAR OS (ADMIN) ======================
    @PostMapping
    public OrdemServicoResponse criar(@RequestBody CriarOSRequest req) {
        OrdemServico os = ordemServicoService.criar(req);
        return mapper.toOrdemServicoResponse(os);
    }

    // ====================== LISTAR (ADMIN = tudo, TÉCNICO = só dele) ======================
    @GetMapping
    public List<OrdemServicoResponse> listar() {
        return ordemServicoService.listar().stream()
                .map(mapper::toOrdemServicoResponse)
                .toList();
    }

    // ====================== BUSCAR POR ID ======================
    @GetMapping("/{id}")
    public OrdemServicoResponse buscar(@PathVariable Long id) {
        OrdemServico os = ordemServicoService.buscar(id);
        return mapper.toOrdemServicoResponse(os);
    }

    // ====================== ATUALIZAR ======================
    @PutMapping("/{id}")
    public OrdemServicoResponse atualizar(@PathVariable Long id,
                                          @RequestBody AtualizarOSRequest req) {
        OrdemServico os = ordemServicoService.atualizar(id, req);
        return mapper.toOrdemServicoResponse(os);
    }

    // ====================== ATRIBUIR TÉCNICO (ADMIN) ======================
    @PutMapping("/{id}/atribuir/{tecnicoId}")
    public OrdemServicoResponse atribuirTecnico(@PathVariable Long id,
                                                @PathVariable Long tecnicoId) {
        OrdemServico os = ordemServicoService.atribuirTecnico(id, tecnicoId);
        return mapper.toOrdemServicoResponse(os);
    }




    // ====================== LISTAR OS DO DIA ATUAL ======================
    @GetMapping("/hoje")
    public List<OrdemServicoResponse> listarHoje() {
        LocalDate hoje = LocalDate.now();
        return ordemServicoService.listarDoDia(hoje).stream()
                .map(mapper::toOrdemServicoResponse)
                .toList();
    }

}
