package com.israelgestaoos.apitechone.dto.auth;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginResponse {

    private String nome;
    private String email;
    private String role;
    private String token;
}
