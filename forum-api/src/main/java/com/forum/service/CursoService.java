package com.forum.service;

import com.forum.dto.convert.CursoConvertDTO;
import com.forum.dto.in.DadosCadastroCurso;
import com.forum.dto.out.DadosDetalhamentoCurso;
import com.forum.exception.RecursoNaoEncontradoException;
import com.forum.model.Curso;
import com.forum.repository.CursoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CursoService {

    @Autowired
    private CursoRepository repository;

    @Autowired
    private CursoConvertDTO cursoConvertDTO;

    public List<DadosDetalhamentoCurso> listar(){
        return cursoConvertDTO.paraDTO(repository.findAll());
    }

    public DadosDetalhamentoCurso buscar(Long id){
        return cursoConvertDTO.paraDTO(buscarEntidade(id));
    }

    public Curso buscarEntidade(Long id){
        return repository.findById(id).orElseThrow(() ->
                new RecursoNaoEncontradoException("Curso não encontrado"));
    }

    @Transactional
    public DadosDetalhamentoCurso salvar(DadosCadastroCurso dados){
        Curso curso = cursoConvertDTO.paraEntidade(dados);
        curso = repository.save(curso);
        return cursoConvertDTO.paraDTO(curso);
    }

    @Transactional
    public DadosDetalhamentoCurso atualizar(Long id, DadosCadastroCurso dados){
        Curso curso = buscarEntidade(id);
        Curso cursoDadosNovo = cursoConvertDTO.paraEntidade(dados);
        BeanUtils.copyProperties(cursoDadosNovo, curso, "id");

        return cursoConvertDTO.paraDTO(curso);
    }

    @Transactional
    public void excluir(Long id){
        buscar(id);
        repository.deleteById(id);
    }
}
