package com.forum.service;

import com.forum.dto.in.DadosCadastroPerfil;
import com.forum.exception.RecursoNaoEncontradoException;
import com.forum.model.Perfil;
import com.forum.repository.PerfilRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PerfilService {

    @Autowired
    private PerfilRepository perfilRepository;

    public List<Perfil> listar(){
        return perfilRepository.findAll();
    }

    public Perfil buscar(Long id){
        return perfilRepository.findById(id).orElseThrow(() ->
                new RecursoNaoEncontradoException("Perfil não encontrado"));
    }

    @Transactional
    public Perfil salvar(DadosCadastroPerfil dados){
        return perfilRepository.save(new Perfil(dados.nome()));
    }

    @Transactional
    public Perfil atualizar(Long id, DadosCadastroPerfil dados){
        Perfil perfil = buscar(id);
        perfil.setNome(dados.nome());

        return perfil;
    }

    @Transactional
    public void remover(Long id){
        buscar(id);
        perfilRepository.deleteById(id);
    }

}
