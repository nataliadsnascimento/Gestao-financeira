package com.financas.gestao_financeira.service;

import com.financas.gestao_financeira.model.ContaModel;
import com.financas.gestao_financeira.model.DespesaModel;
import com.financas.gestao_financeira.repository.ContaRepository;
import com.financas.gestao_financeira.repository.DespesaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class DespesaService {

    @Autowired
    private DespesaRepository despesaRepository;

    @Autowired
    private ContaRepository contaRepository;

    @Transactional
    public DespesaModel salvar(DespesaModel despesa){
        ContaModel conta = despesa.getConta();

        if (conta == null || conta.getId() == null){
            throw new RuntimeException("A despesa deve estar associada a uma conta válida.");
        }

        UUID contaId = conta.getId();

        ContaModel contaBanco = contaRepository.findById(contaId)
                .orElseThrow(() -> new RuntimeException("Conta não encontrada."));

        contaBanco.setSaldo(contaBanco.getSaldo().subtract(despesa.getValor()));
        contaRepository.save(contaBanco);

        return despesaRepository.save(despesa);
    }

    public List<DespesaModel> listar(){
        return despesaRepository.findAll();
    }

    public DespesaModel buscarPorId(UUID id){
        return despesaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Despesa não encontrada"));
    }

    @Transactional
    public void deletar(UUID id){
        DespesaModel despesa = buscarPorId(id);

        ContaModel conta = despesa.getConta();
        conta.setSaldo(conta.getSaldo().add(despesa.getValor()));

        contaRepository.save(conta);
        despesaRepository.delete(despesa);
    }
}