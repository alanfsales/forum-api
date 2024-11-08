package com.forum.model;

import com.forum.dto.in.DadosCadastroCurso;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
public class Curso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String categoria;

    public Curso(String nome, String categoria){
        this.nome = nome;
        this.categoria = categoria;
    }

    public Curso(DadosCadastroCurso dados){
        this.nome = dados.nome();
        this.categoria = dados.categoria();
    }
}
