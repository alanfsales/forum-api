package com.forum.dto.out;

import com.forum.model.Perfil;
import com.forum.model.Usuario;

public record DadosDetalhamentoUsuario(
        Long id, String nome, String email, Perfil perfil) {

    public DadosDetalhamentoUsuario(Usuario usuario){
        this(usuario.getId(), usuario.getNome(), usuario.getEmail(), usuario.getPerfil());
    }
}
