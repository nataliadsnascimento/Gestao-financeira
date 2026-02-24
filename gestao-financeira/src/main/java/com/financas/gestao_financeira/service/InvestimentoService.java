package com.financas.gestao_financeira.service;

import com.financas.gestao_financeira.model.InvestimentoModel;
import com.financas.gestao_financeira.repository.InvestimentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class InvestimentoService {

    @Autowired
    public InvestimentoRepository investimentoRepository;

    public InvestimentoModel salvar(InvestimentoModel investimento) {
        return investimentoRepository.save(investimento);
    }

    public List<InvestimentoModel> Listar(UUID usuarioId) {
        return investimentoRepository.findByUsuarioId(usuarioId);
    }

    public InvestimentoModel atualizar(UUID id, InvestimentoModel investimentoAtualizado) {
        InvestimentoModel investimentoExistente = investimentoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Investimento não encontrado com o ID: " + id));

        investimentoExistente.setNomeAtivo(investimentoAtualizado.getNomeAtivo());
        investimentoExistente.setInstituicao(investimentoAtualizado.getInstituicao());
        investimentoExistente.setValorInvestido(investimentoAtualizado.getValorInvestido());
        investimentoExistente.setDataAplicacao(investimentoAtualizado.getDataAplicacao());

        if (investimentoAtualizado.getConta() != null) {
            investimentoExistente.setConta(investimentoAtualizado.getConta());
        }

        return investimentoRepository.save(investimentoExistente);
    }

    public void deletar(UUID id) {
        investimentoRepository.deleteById(id);
    }
}