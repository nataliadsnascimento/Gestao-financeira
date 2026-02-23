package com.financas.gestao_financeira.service;

import com.financas.gestao_financeira.model.UsuarioModel;
import com.financas.gestao_financeira.repository.UsuarioRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    //Método de salvar
    public UsuarioModel salvar(UsuarioModel usuario) {
        Optional<UsuarioModel> usuarioExistente = usuarioRepository.findByEmail(usuario.getEmail());
        if (usuarioExistente.isPresent()) {
            throw new RuntimeException("E-mail já cadastrado no sistema.");
        }
        return usuarioRepository.save(usuario);
    }

    //Lista todos
    public List<UsuarioModel> listarTodos() {
        return usuarioRepository.findAll();
    }

    public UsuarioModel buscarPorId(UUID id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado para o ID: " + id));
    }

    //Atualizar
    public UsuarioModel atualizar(UUID id, UsuarioModel usuarioDadosNovos) {
        UsuarioModel usuarioExistente = buscarPorId(id);

        usuarioExistente.setEmail(usuarioDadosNovos.getEmail());
        usuarioExistente.setNome(usuarioDadosNovos.getNome());
        usuarioExistente.setSenha(usuarioDadosNovos.getSenha());

        return usuarioRepository.save(usuarioExistente);
    }

    public UsuarioModel autenticar(String email, String senha) {
        UsuarioModel usuario = usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("E-mail não encontrado"));

        if (!usuario.getSenha().equals(senha)) {
            throw new RuntimeException("Senha incorreta");
        }
        return usuario;
    }

    @Transactional
    public void deletar(UUID id) {
       if (!usuarioRepository.existsById(id)) {
           throw new RuntimeException("Usuário não encontrado");
       }
        usuarioRepository.deleteById(id);
    }
}
