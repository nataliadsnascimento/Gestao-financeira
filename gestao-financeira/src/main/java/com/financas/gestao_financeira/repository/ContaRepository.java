package com.financas.gestao_financeira.repository;

import com.financas.gestao_financeira.model.ContaModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.UUID;

@Repository
public interface ContaRepository extends JpaRepository<ContaModel, UUID> {
}