package com.forum.dto.out;

import com.forum.model.Curso;

public record DadosDetalhamentoCurso(
        Long id,
        String nome,
        String categoria ){

    public DadosDetalhamentoCurso(Curso curso){
        this(curso.getId(), curso.getNome(), curso.getCategoria());
    }
}
