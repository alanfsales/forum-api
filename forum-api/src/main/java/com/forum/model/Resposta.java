package com.forum.model;

import com.forum.dto.in.DadosCadastroResposta;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Entity
@NoArgsConstructor
public class Resposta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String mensagem;

    @ManyToOne
    private Topico topico;

    private LocalDateTime dataCriacao;

    @ManyToOne
    private Usuario autor;
    private Boolean solucao;

    public Resposta(DadosCadastroResposta dados, Topico topico, Usuario autor){
        this.mensagem = dados.mensagem();
        this.topico = topico;
        this.dataCriacao = LocalDateTime.now();
        this.autor = autor;
        this.solucao = false;
    }

}
