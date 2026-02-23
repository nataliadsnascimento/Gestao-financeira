package com.financas.gestao_financeira.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;
import java.math.BigDecimal;


@Data
@Entity
@Table(name = "Contas")
public class ContaModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @Column(nullable = false)
    private String nome;

    private BigDecimal saldo;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private UsuarioModel usuario;
}
