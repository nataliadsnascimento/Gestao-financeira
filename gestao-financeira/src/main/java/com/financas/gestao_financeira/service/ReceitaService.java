package com.financas.gestao_financeira.service;

import com.financas.gestao_financeira.model.ReceitaModel;
import com.financas.gestao_financeira.model.ContaModel;
import com.financas.gestao_financeira.repository.ReceitaRepository;
import com.financas.gestao_financeira.repository.ContaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class ReceitaService {

    @Autowired
    private ReceitaRepository receitaRepository;

    @Autowired
    private ContaRepository contaRepository;

    @Transactional
    public ReceitaModel salvar(ReceitaModel receita) {
        ContaModel conta = receita.getConta();

        if (conta != null) {
            throw new RuntimeException("A receita deve estar associada a uma conta!");
        }

        conta.setSaldo(conta.getSaldo().add(receita.getValor()));
        contaRepository.save(conta);

        return receitaRepository.save(receita);
    }

    public ReceitaModel buscarPorId(UUID id) {
        return receitaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Receita não encontrada"));
    }

    public List<ReceitaModel> listarTodas() {
        return receitaRepository.findAll();
    }

    @Transactional
    public void deletar(UUID id) {
        ReceitaModel receita = buscarPorId(id);

        ContaModel conta = receita.getConta();
        conta.setSaldo(conta.getSaldo().subtract(receita.getValor()));

        contaRepository.save(conta);
        receitaRepository.delete(receita);
    }
}