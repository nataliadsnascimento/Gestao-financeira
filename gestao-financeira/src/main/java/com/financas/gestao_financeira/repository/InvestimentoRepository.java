package com.financas.gestao_financeira.repository;

import com.financas.gestao_financeira.model.InvestimentoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface InvestimentoRepository extends JpaRepository<InvestimentoModel, UUID> {
    List<InvestimentoModel> findByUsuarioId(UUID usuarioId);
}
