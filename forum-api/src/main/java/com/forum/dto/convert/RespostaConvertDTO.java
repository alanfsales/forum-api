package com.forum.dto.convert;

import com.forum.dto.out.DadosDetalhamentoResposta;
import com.forum.model.Resposta;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RespostaConvertDTO {

    public List<DadosDetalhamentoResposta> paraDTO(List<Resposta> respostas){
        return respostas.stream().map(DadosDetalhamentoResposta::new).toList();
    }

    public DadosDetalhamentoResposta paraTDO(Resposta resposta){
        return new DadosDetalhamentoResposta(resposta);
    }
}
