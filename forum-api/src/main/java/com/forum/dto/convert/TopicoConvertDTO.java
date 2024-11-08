package com.forum.dto.convert;

import com.forum.dto.in.DadosCadastroTopico;
import com.forum.dto.out.DadosDetalhamentoTopico;
import com.forum.model.Curso;
import com.forum.model.Topico;
import com.forum.model.Usuario;
import com.forum.service.CursoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TopicoConvertDTO {

    @Autowired
    private CursoService cursoService;

    public DadosDetalhamentoTopico paraDTO(Topico topico){
        return new DadosDetalhamentoTopico(topico);
    }

    public List<DadosDetalhamentoTopico> paraDTO(List<Topico> topicos){
        return topicos.stream().map(DadosDetalhamentoTopico::new).toList();
    }

    public Topico paraTopico(DadosCadastroTopico dados, Usuario usuario){
        Curso curso = cursoService.buscarEntidade(dados.curso_id());
        return new Topico(dados, curso, usuario);
    }
}
