package com.israelgestaoos.apitechone.dto.os;

import com.israelgestaoos.apitechone.model.enums.Prioridade;
import com.israelgestaoos.apitechone.model.enums.StatusOS;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class OrdemServicoResponse {

    private Long id;

    private String titulo;
    private String descricao;

    private Long clienteId;
    private String clienteNome;

    private Long tecnicoId;
    private String tecnicoNome;

    private Prioridade prioridade;
    private StatusOS status;

    private LocalDateTime criadoEm;
    private LocalDateTime dataAgendada;

    private boolean agendada;
}
