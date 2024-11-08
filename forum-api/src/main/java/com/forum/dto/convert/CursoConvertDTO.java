package com.forum.dto.convert;

import com.forum.dto.in.DadosCadastroCurso;
import com.forum.dto.out.DadosDetalhamentoCurso;
import com.forum.model.Curso;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CursoConvertDTO {

    public Curso paraEntidade(DadosCadastroCurso dados){
        return new Curso(dados);
    }

    public DadosDetalhamentoCurso paraDTO(Curso curso){
        return new DadosDetalhamentoCurso(curso);
    }

    public List<DadosDetalhamentoCurso> paraDTO(List<Curso> cursos){
        return cursos.stream().map(DadosDetalhamentoCurso::new).toList();
    }

}
