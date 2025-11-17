package com.israelgestaoos.apitechone.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class OrdemServicoRequest {

    private String titulo;
    private String descricao;

    private Long clienteId;
    private Long tecnicoId;
    private Long prioridadeId;
    private Long statusId;

    // NOVO — agendamento opcional
    private LocalDateTime dataAgendada;
}
