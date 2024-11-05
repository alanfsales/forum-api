package com.forum.dto.convert;

import com.forum.dto.DadosCadastroUsuario;
import com.forum.dto.DadosDetalhamentoUsuario;
import com.forum.model.Perfil;
import com.forum.model.Usuario;
import com.forum.service.PerfilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UsuarioConvertDTO {

    @Autowired
    private PerfilService perfilService;

    public List<DadosDetalhamentoUsuario> paraDTO(List<Usuario> usuarios){
        return usuarios.stream()
                .map(DadosDetalhamentoUsuario::new)
                .toList();
    }

    public DadosDetalhamentoUsuario paraTDO(Usuario usuario){
        return new DadosDetalhamentoUsuario(usuario);
    }

    public Usuario paraUsuario(DadosCadastroUsuario dados){
        Perfil perfil = perfilService.buscar(dados.perfilId());
        return new Usuario(dados, perfil);
    }
}
