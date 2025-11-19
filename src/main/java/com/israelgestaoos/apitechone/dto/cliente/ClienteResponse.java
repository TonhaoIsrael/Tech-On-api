package com.israelgestaoos.apitechone.dto.cliente;

import lombok.Data;

@Data
public class ClienteResponse {

    private Long id;
    private String nome;
    private String telefone;
    private String email;
    private String endereco;
    private boolean ativo;
}
