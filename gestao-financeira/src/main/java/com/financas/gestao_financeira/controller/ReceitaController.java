package com.financas.gestao_financeira.controller;

import com.financas.gestao_financeira.model.ReceitaModel;
import com.financas.gestao_financeira.service.ReceitaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/receitas")
public class ReceitaController {

    @Autowired
    private ReceitaService receitaService;

    @PostMapping
    public ResponseEntity<ReceitaModel> salvar(@RequestBody ReceitaModel receita) {
        return new ResponseEntity<>(receitaService.salvar(receita), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ReceitaModel>> listar() {
        return ResponseEntity.ok(receitaService.listarTodas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReceitaModel> buscarPorId(@PathVariable UUID id) {
        return ResponseEntity.ok(receitaService.buscarPorId(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable UUID id) {
        receitaService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
