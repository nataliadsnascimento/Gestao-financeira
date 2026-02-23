package com.financas.gestao_financeira.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Data
@Entity
public class UsuarioModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String nome;
    @Column(nullable = false, unique = true)
    private String email;
    private String senha;
}
