package com.israelgestaoos.apitechone.dto.os;

import com.israelgestaoos.apitechone.model.enums.Prioridade;
import com.israelgestaoos.apitechone.model.enums.StatusOS;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class OrdemServicoRequest {

    private String titulo;
    private String descricao;

    private Long clienteId;
    private Long tecnicoId;

    private Prioridade prioridade;
    private StatusOS status;

    private LocalDateTime dataAgendada;
}
