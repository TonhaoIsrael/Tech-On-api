package com.israelgestaoos.apitechone.dto;

import lombok.Data;

@Data
public class ClienteRequest {
    private String nome;
    private String telefone;
    private String email;
    private String endereco;
}
