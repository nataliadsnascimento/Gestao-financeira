package com.financas.gestao_financeira.controller;

import com.financas.gestao_financeira.model.DespesaModel;
import com.financas.gestao_financeira.service.DespesaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/despesas")
public class DespesaController {

    @Autowired
    public DespesaService despesaService;

    @PostMapping
    public ResponseEntity<DespesaModel> salvar(@RequestBody DespesaModel despesa) {
        return new ResponseEntity<>(despesaService.salvar(despesa), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<DespesaModel>> listar() {
        return ResponseEntity.ok(despesaService.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DespesaModel> buscarPorId(@PathVariable UUID id) {
        return ResponseEntity.ok(despesaService.buscarPorId(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable UUID id) {
        despesaService.deletar(id);
        return ResponseEntity.noContent().build();
    }

}
