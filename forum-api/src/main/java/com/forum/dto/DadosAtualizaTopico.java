package com.forum.dto;

public record DadosAtualizaTopico(
    String titulo,
    String mensagem,
    Long autor_id,
    Long curso_id) {

}
