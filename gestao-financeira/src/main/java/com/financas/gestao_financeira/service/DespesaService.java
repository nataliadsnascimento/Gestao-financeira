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
    public void salvar(DespesaModel despesa) {
        if (despesa.getConta() == null || despesa.getConta().getId() == null) {
            throw new RuntimeException("A despesa deve estar associada a uma conta válida.");
        }

        int total = (despesa.getTotalParcelas() != null && despesa.getTotalParcelas() > 1)
                ? despesa.getTotalParcelas() : 1;

        if (total > 1) {
            salvarDespesaParcelada(despesa, total);
        } else {
            despesa.setParcelaAtual(1);
            despesa.setTotalParcelas(1);
            processarSalvarUnico(despesa);
        }
    }

    private void salvarDespesaParcelada(DespesaModel despesaOriginal, int total) {
        String grupamentoId = UUID.randomUUID().toString();

        for (int i = 1; i <= total; i++) {
            DespesaModel parcela = new DespesaModel();

            parcela.setValor(despesaOriginal.getValor());
            parcela.setDescricao(despesaOriginal.getDescricao() + " (" + i + "/" + total + ")");
            parcela.setCategoria(despesaOriginal.getCategoria());
            parcela.setUsuario(despesaOriginal.getUsuario());
            parcela.setConta(despesaOriginal.getConta());
            parcela.setFormaPagamento(despesaOriginal.getFormaPagamento());

            parcela.setData(despesaOriginal.getData().plusMonths(i - 1));

            parcela.setParcelaAtual(i);
            parcela.setTotalParcelas(total);
            parcela.setIdentificadorGrupamento(grupamentoId);

            processarSalvarUnico(parcela);
        }
    }

    private void processarSalvarUnico(DespesaModel despesa) {
        ContaModel contaBanco = contaRepository.findById(despesa.getConta().getId())
                .orElseThrow(() -> new RuntimeException("Conta não encontrada."));

        contaBanco.setSaldo(contaBanco.getSaldo().subtract(despesa.getValor()));

        contaRepository.save(contaBanco);
        despesaRepository.save(despesa);
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