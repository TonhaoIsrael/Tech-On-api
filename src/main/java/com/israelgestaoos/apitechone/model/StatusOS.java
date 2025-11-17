package com.israelgestaoos.apitechone.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "status_os")
public class StatusOS {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    // opcional
    private boolean finaliza;
}
