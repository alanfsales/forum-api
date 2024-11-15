package com.forum.dto.out;

import com.forum.model.Topico;

import java.time.LocalDateTime;
import java.util.List;

public record DadosDetalhamentoTopico(
        Long id,
        String titulo,
        String mensagem,
        LocalDateTime dataCriacao,
        String status,
        String nomeCurso,
        String nomeAutor,
        List<DadosDetalhamentoResposta> respostas
        ) {

    public DadosDetalhamentoTopico(Topico topico){
        this(topico.getId(), topico.getTitulo(), topico.getMensagem(), topico.getDataCriacao(),
                topico.getStatus(), topico.getCurso().getNome(), topico.getAutor().getNome(),
                topico.getRespostas().stream().map(DadosDetalhamentoResposta::new).toList());
    }
}
