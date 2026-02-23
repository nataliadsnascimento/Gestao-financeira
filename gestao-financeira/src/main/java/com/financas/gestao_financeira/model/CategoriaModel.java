package com.financas.gestao_financeira.model;

import jakarta.persistence.*;
import lombok.Data;
import java.util.UUID;

@Data
@Entity
public class CategoriaModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String nome;

    @Enumerated(EnumType.STRING)
    private TipoCategoria tipo;
}
