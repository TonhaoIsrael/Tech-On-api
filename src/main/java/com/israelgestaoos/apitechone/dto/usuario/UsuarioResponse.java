package com.israelgestaoos.apitechone.dto.usuario;

import lombok.Data;

@Data
public class UsuarioResponse {

    private Long id;
    private String nome;
    private String email;
    private String role;   // Nome da role (ADMIN, TECNICO, CLIENTE...)
    private boolean ativo;
}
