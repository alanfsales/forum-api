package com.forum.dto.out;

import com.forum.model.Resposta;

import java.time.LocalDateTime;

public record DadosDetalhamentoResposta(
        Long id,
        String mensagem,
        LocalDateTime dataCriacao,
        String nomeAutor
        ) {

        public DadosDetalhamentoResposta(Resposta resposta){
                this(resposta.getId(), resposta.getMensagem(),
                        resposta.getDataCriacao(), resposta.getAutor().getNome());
        }
}
