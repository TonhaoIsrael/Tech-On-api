package com.israelgestaoos.apitechone.dto.usuario;

import lombok.Data;

@Data
public class UsuarioRequest {
    private String nome;
    private String email;
    private String senha;
    private Long roleId;
}
