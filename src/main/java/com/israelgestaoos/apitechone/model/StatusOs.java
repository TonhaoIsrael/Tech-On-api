package com.israelgestaoos.apitechone.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "status_os")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class StatusOs {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 30)
    private String nome;

    @Column(nullable = false)
    private boolean finaliza;
}
