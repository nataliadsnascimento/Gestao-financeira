package com.financas.gestao_financeira.controller;

import com.financas.gestao_financeira.model.InvestimentoModel;
import com.financas.gestao_financeira.service.InvestimentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/investimentos")
public class InvestimentoController {
    @Autowired
    private InvestimentoService investimentoService;

    @PostMapping
    public ResponseEntity<InvestimentoModel> salvar(@RequestBody InvestimentoModel investimentoModel) {
        return ResponseEntity.ok(investimentoService.salvar(investimentoModel));
    }

    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<InvestimentoModel>> listar(@PathVariable UUID usuarioId) {
        return ResponseEntity.ok(investimentoService.Listar(usuarioId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<InvestimentoModel> atualizar(@PathVariable UUID id, @RequestBody InvestimentoModel investimento) {
        return ResponseEntity.ok(investimentoService.atualizar(id, investimento));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable UUID id) {
        investimentoService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
