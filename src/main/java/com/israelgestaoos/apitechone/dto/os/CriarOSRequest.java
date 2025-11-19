package com.israelgestaoos.apitechone.dto.os;

import com.israelgestaoos.apitechone.model.enums.Prioridade;
import com.israelgestaoos.apitechone.model.enums.StatusOS;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CriarOSRequest {

    private String titulo;
    private String descricao;

    private Long clienteId;   // obrigatório
    private Long tecnicoId;   // opcional

    private Prioridade prioridade; // ENUM
    private StatusOS status;       // ENUM

    private LocalDateTime dataAgendada;
}
