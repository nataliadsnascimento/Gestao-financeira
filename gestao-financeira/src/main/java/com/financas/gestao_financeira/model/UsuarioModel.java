package com.financas.gestao_financeira.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class UsuarioModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    @Column(nullable = false, unique = true)
    private String email;
    private String senha;
}
