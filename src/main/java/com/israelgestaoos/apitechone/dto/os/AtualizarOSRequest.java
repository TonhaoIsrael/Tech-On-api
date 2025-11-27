package com.israelgestaoos.apitechone.dto.os;

import com.israelgestaoos.apitechone.model.Usuario;
import com.israelgestaoos.apitechone.model.enums.Prioridade;
import com.israelgestaoos.apitechone.model.enums.StatusOS;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AtualizarOSRequest {

    private LocalDateTime dataAtualizacao;
    private Usuario tecnico;
    private String titulo;
    private String descricao;
    private Prioridade prioridade;
    private StatusOS status;
}
