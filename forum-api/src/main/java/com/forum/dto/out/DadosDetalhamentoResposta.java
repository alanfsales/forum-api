package com.forum.dto.out;

import com.forum.model.Resposta;

import java.time.LocalDateTime;

public record DadosDetalhamentoResposta(
        Long id,
        String tituloTopico,
        String mensagem,
        LocalDateTime dataCriacao,
        String nomeAutor,
        String nomeCurso
        ) {

        public DadosDetalhamentoResposta(Resposta resposta){
                this(resposta.getId(), resposta.getTopico().getTitulo(), resposta.getMensagem(),
                        resposta.getDataCriacao(), resposta.getAutor().getNome(),
                        resposta.getTopico().getCurso().getNome());
        }
}
