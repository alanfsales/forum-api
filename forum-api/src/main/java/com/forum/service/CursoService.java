package com.forum.service;

import com.forum.dto.DadosCadastroCurso;
import com.forum.exception.RecursoNaoEncontradoException;
import com.forum.model.Curso;
import com.forum.repository.CursoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CursoService {

    @Autowired
    private CursoRepository repository;

    public List<Curso> listar(){
        return repository.findAll();
    }

    public Curso buscar(Long id){
        return repository.findById(id).orElseThrow(() ->
                new RecursoNaoEncontradoException("Curso não encontrado"));
    }

    @Transactional
    public Curso salvar(DadosCadastroCurso dados){
        return repository.save(new Curso(dados.nome(), dados.categoria()));
    }

    @Transactional
    public Curso atualizar(Long id, DadosCadastroCurso dado){
        Curso curso = buscar(id);
        curso.setNome(dado.nome());
        curso.setCategoria(dado.categoria());
        return curso;
    }

    @Transactional
    public void excluir(Long id){
        buscar(id);
        repository.deleteById(id);
    }
}
