package com.financas.gestao_financeira.service;

import com.financas.gestao_financeira.model.ContaModel;
import com.financas.gestao_financeira.repository.ContaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ContaService {

    @Autowired
    private ContaRepository contaRepository;

    public ContaModel salvar(ContaModel conta){
        if (conta.getSaldo() == null) {
            conta.setSaldo(java.math.BigDecimal.ZERO);
        }
        return contaRepository.save(conta);
    }

    public List<ContaModel> listar() {
        return contaRepository.findAll();
    }

    public ContaModel buscarPorId(UUID id) {
        return contaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Conta não encontrada"));
    }

    public ContaModel atualizar(UUID id, ContaModel detalhesConta){
        ContaModel contaExistente = buscarPorId(id);

        contaExistente.setNome(detalhesConta.getNome());
        contaExistente.setSaldo(detalhesConta.getSaldo());

        return contaRepository.save(contaExistente);
    }

    public void deletar(UUID id) {
        ContaModel conta = buscarPorId(id);
        contaRepository.delete(conta);
    }

}
