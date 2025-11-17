package com.israelgestaoos.apitechone.model;

import com.israelgestaoos.apitechone.model.enums.Prioridade;
import com.israelgestaoos.apitechone.model.enums.StatusOS;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;


@Entity
@Table(name = "ordens_servico")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrdemServico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "tecnico_id")
    private Usuario tecnico;

    @Column(nullable = false, length = 255)
    private String descricao;

    @Column(nullable = false, length = 120)
    private String titulo;


    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Prioridade prioridade;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusOS status = StatusOS.ABERTO;

    @Column(name = "criado_em", nullable = false)
    private LocalDateTime criado_em = LocalDateTime.now();

    @Column(name = "data_agendada")
    private LocalDateTime dataAgendada;

    @Column(nullable = false)
    private boolean agendada = false;
}
