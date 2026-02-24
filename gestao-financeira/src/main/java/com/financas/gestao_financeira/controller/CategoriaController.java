package com.financas.gestao_financeira.controller;

import com.financas.gestao_financeira.model.CategoriaModel;
import com.financas.gestao_financeira.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;

    @PostMapping
    public ResponseEntity<CategoriaModel> inserir(@RequestBody CategoriaModel categoria) {
        return new ResponseEntity<>(categoriaService.salvar(categoria), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<CategoriaModel>> listar() {
        return ResponseEntity.ok(categoriaService.listar());
    }


    @GetMapping("{id}")
    public ResponseEntity<CategoriaModel> buscarPorId(@PathVariable UUID id)  {
        return ResponseEntity.ok(categoriaService.buscarPorId(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable UUID id) {
        categoriaService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
