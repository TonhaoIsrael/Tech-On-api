package com.israelgestaoos.apitechone.model;

import com.israelgestaoos.apitechone.model.enums.Prioridade;
import com.israelgestaoos.apitechone.model.enums.StatusOS;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "ordens_servico")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrdemServico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // RELACIONAMENTO COM CLIENTE (obrigatório)
    @ManyToOne(optional = false)
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;

    // RELACIONAMENTO COM TÉCNICO (opcional)
    @ManyToOne
    @JoinColumn(name = "tecnico_id")
    private Usuario tecnico;

    @Column(nullable = false, length = 120)
    private String titulo;

    @Column(nullable = false, length = 255)
    private String descricao;

    // ENUM PRIORIDADE
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Prioridade prioridade;

    // ENUM STATUS
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusOS status = StatusOS.ABERTO;

    // DATA DE CRIAÇÃO (não atualiza)
    @Column(name = "criado_em", nullable = false, updatable = false)
    private LocalDateTime criadoEm;

    // DATA AGENDADA (opcional)
    @Column(name = "data_agendada")
    private LocalDateTime dataAgendada;

    // FLAG SE FOI AGENDADA (controlado no service)
    @Column(nullable = false)
    private boolean agendada = false;

    // ================================
    //  HOOK PARA DEFINIR DATA CRIADO
    // ================================
    @PrePersist
    protected void onCreate() {
        this.criadoEm = LocalDateTime.now();
    }
}
