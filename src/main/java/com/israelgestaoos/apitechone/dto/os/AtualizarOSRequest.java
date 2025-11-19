package com.israelgestaoos.apitechone.dto.os;

import com.israelgestaoos.apitechone.model.enums.Prioridade;
import com.israelgestaoos.apitechone.model.enums.StatusOS;
import lombok.Data;

@Data
public class AtualizarOSRequest {

    private String descricao;
    private Prioridade prioridade;
    private StatusOS status;
}
