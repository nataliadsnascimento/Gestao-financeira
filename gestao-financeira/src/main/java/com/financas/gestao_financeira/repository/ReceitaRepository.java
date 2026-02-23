package com.financas.gestao_financeira.repository;

import com.financas.gestao_financeira.model.ReceitaModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ReceitaRepository extends JpaRepository<ReceitaModel, UUID> {
}
