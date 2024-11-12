package com.forum.service;

import com.forum.dto.convert.UsuarioConvertDTO;
import com.forum.dto.in.DadosCadastroUsuario;
import com.forum.dto.out.DadosDetalhamentoUsuario;
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
    private UsuarioConvertDTO usuarioConvertDTO;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<DadosDetalhamentoUsuario> listar(){
        return usuarioConvertDTO.paraDTO(usuarioRepository.findAll());
    }

    public DadosDetalhamentoUsuario buscar(Long id) {
        return usuarioConvertDTO.paraTDO(buscarEntidade(id));
    }

    public Usuario buscarEntidade(Long id) {
        return usuarioRepository.findById(id).orElseThrow(
                () -> new RecursoNaoEncontradoException("Usuario não encontrado"));
    }

    public Usuario buscarPorEmail(String email) {
        return (Usuario) usuarioRepository.findByEmail(email);
    }

    @Transactional
    public DadosDetalhamentoUsuario salvar(DadosCadastroUsuario dados) {
        Usuario usuario = usuarioConvertDTO.paraEntidade(dados);
        usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));
        return usuarioConvertDTO.paraTDO(usuarioRepository.save(usuario));
    }

    @Transactional
    public DadosDetalhamentoUsuario atualizar(Long id, DadosCadastroUsuario dados) {
        Usuario usuarioBD = buscarEntidade(id);
        Usuario usuarioDadosNovo = usuarioConvertDTO.paraEntidade(dados);
        BeanUtils.copyProperties(usuarioDadosNovo, usuarioBD, "id");
        usuarioBD.setSenha(passwordEncoder.encode(usuarioBD.getSenha()));

        return usuarioConvertDTO.paraTDO(usuarioBD);
    }

    @Transactional
    public void excluir(Long id){
        buscarEntidade(id);
        usuarioRepository.deleteById(id);
    }
}
