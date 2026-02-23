package com.financas.gestao_financeira.service;

import com.financas.gestao_financeira.model.CategoriaModel;
import com.financas.gestao_financeira.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    public CategoriaModel salvar(CategoriaModel categoria) {
        return categoriaRepository.save(categoria);
    }

    public List<CategoriaModel> listar(){
        return categoriaRepository.findAll();
    }

    public CategoriaModel buscarPorId(UUID id){
        return categoriaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Categoria não encontrada"));
    }

    public void deletar(UUID id) {
        CategoriaModel categoriaModel = buscarPorId(id);
        categoriaRepository.delete(categoriaModel);
    }
}