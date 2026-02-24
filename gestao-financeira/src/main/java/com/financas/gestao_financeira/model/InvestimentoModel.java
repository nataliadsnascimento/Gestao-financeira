package com.financas.gestao_financeira.model;

import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDate;

import java.util.UUID;

@Entity
@Data
@Table(name= "tb_investimento")
public class InvestimentoModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false)
    private String nomeAtivo;

    @Column(nullable = false)
    private String valorInvestido;

    @Column(nullable = false)
    private String instituicao;

    @Column(nullable = false)
    private String dataAplicacao;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private UsuarioModel usuario;

    @ManyToOne
    @JoinColumn(name = "conta_id")
    private ContaModel conta;
}