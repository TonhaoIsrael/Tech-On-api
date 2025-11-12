package com.israelgestaoos.apitechone.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "prioridades")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Prioridade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 20)
    private String nome;

    @Column(nullable = false)
    private Integer ordem;
}
