package com.financas.gestao_financeira.controller;

import com.financas.gestao_financeira.model.ContaModel;
import com.financas.gestao_financeira.service.ContaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/contas")
public class ContaController {

    @Autowired
    private ContaService contaService;

    @PostMapping
    public ResponseEntity<ContaModel> criar(@RequestBody ContaModel conta) {
        return new ResponseEntity<>(contaService.salvar(conta), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ContaModel>> listar() {
        return ResponseEntity.ok(contaService.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ContaModel> buscarPorId(@PathVariable UUID id) {
        return ResponseEntity.ok(contaService.buscarPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ContaModel> atualizar(@PathVariable UUID id, @RequestBody ContaModel detalhesConta) {
        return ResponseEntity.ok(contaService.atualizar(id, detalhesConta));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ContaModel> deletar(@PathVariable UUID id) {
        contaService.deletar(id);
        return ResponseEntity.noContent().build();
    }

}
