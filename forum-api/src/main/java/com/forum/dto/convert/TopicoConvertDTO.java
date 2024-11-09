package com.forum.dto.convert;

import com.forum.dto.in.DadosCadastroTopico;
import com.forum.dto.out.DadosDetalhamentoTopico;
import com.forum.model.Curso;
import com.forum.model.Topico;
import com.forum.model.Usuario;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TopicoConvertDTO {

    public DadosDetalhamentoTopico paraDTO(Topico topico){
        return new DadosDetalhamentoTopico(topico);
    }

    public List<DadosDetalhamentoTopico> paraDTO(List<Topico> topicos){
        return topicos.stream().map(DadosDetalhamentoTopico::new).toList();
    }

    public Topico paraEntidade(DadosCadastroTopico dados, Usuario usuario, Curso curso){
        return new Topico(dados, curso, usuario);
    }
}
