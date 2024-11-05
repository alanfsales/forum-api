package com.forum.service;

import com.forum.exception.RecursoNaoEncontradoException;
import com.forum.model.Usuario;
import com.forum.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<Usuario> listar(){;
        return usuarioRepository.findAll();
    }

    public Usuario buscar(Long id) {
        return usuarioRepository.findById(id).orElseThrow(
                () -> new RecursoNaoEncontradoException("Usuario não encontrado"));
    }

    public Usuario buscarPorEmail(String email) {
        return (Usuario) usuarioRepository.findByEmail(email);
    }

    @Transactional
    public Usuario salvar(Usuario usuario) {
        usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));
        return usuarioRepository.save(usuario);
    }

    @Transactional
    public Usuario atualizar(Long id, Usuario usuario) {
        Usuario usuarioBD = buscar(id);
        BeanUtils.copyProperties(usuario, usuarioBD, "id");

        return salvar(usuarioBD);
    }

    @Transactional
    public void excluir(Long id){
        buscar(id);
        usuarioRepository.deleteById(id);
    }
}
