package com.forum.service;

import com.forum.dto.DadosAtualizaTopico;
import com.forum.dto.DadosCadastroTopico;
import com.forum.dto.DadosDetalhamentoTopico;
import com.forum.exception.ValidacaoException;
import com.forum.model.Topico;
import com.forum.repository.CursoRepository;
import com.forum.repository.TopicoRepository;
import com.forum.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TopicoService {

    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private CursoRepository cursoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Transactional
    public DadosDetalhamentoTopico salvar(DadosCadastroTopico dados){
        validarDados(dados);

        var curso = cursoRepository.getReferenceById(dados.curso_id());
        var autor = usuarioRepository.getReferenceById(dados.autor_id());
        var topico = new Topico(dados.titulo(), dados.mensagem(), curso, autor);

        topico = topicoRepository.save(topico);

        var dadosDetalhementoTopico = new DadosDetalhamentoTopico(topico);

        return dadosDetalhementoTopico;
    }

    public DadosDetalhamentoTopico buscar(Long id){
        if (!topicoRepository.existsById(id)){
            throw new ValidacaoException("Tópico não encontrado");
        }

        return new DadosDetalhamentoTopico(topicoRepository.getReferenceById(id));
    }

    public List<DadosDetalhamentoTopico> listar(){
        List<Topico>topicos = topicoRepository.findAllByOrderByDataCriacaoDesc();

        return topicos.stream().map(t -> new DadosDetalhamentoTopico(t)).toList();
    }

    @Transactional
    public DadosDetalhamentoTopico atualizar(Long id, DadosAtualizaTopico dados) {
        buscar(id);

        var topico = topicoRepository.getReferenceById(id);

        if (dados.titulo() != null){
            if (topicoRepository.existsByTitulo(dados.titulo())){
                throw new ValidacaoException("Já existe um tópico com esse titulo");
            }
            topico.setTitulo(dados.titulo());
        }
        if (dados.mensagem() != null){
            if (topicoRepository.existsByMensagem(dados.mensagem())){
                throw new ValidacaoException("Já existe um tópico com essa mensagem");
            }
            topico.setMensagem(dados.mensagem());
        }
        if (dados.curso_id() != null){
            if (!cursoRepository.existsById(dados.curso_id())){
                throw new ValidacaoException("Curso não encontado");
            }
            var curso = cursoRepository.getReferenceById(dados.curso_id());
            topico.setCurso(curso);
        }
        if (dados.autor_id() != null){
            if (!usuarioRepository.existsById(dados.autor_id())){
                throw new ValidacaoException("Autor não encontado");
            }
            var autor = usuarioRepository.getReferenceById(dados.curso_id());
            topico.setAutor(autor);
        }

        return new DadosDetalhamentoTopico(topico);
    }

    @Transactional
    public void remover(Long id) {
        buscar(id);
        topicoRepository.deleteById(id);
    }

    private void validarDados(DadosCadastroTopico dados) {
        if (!cursoRepository.existsById(dados.curso_id())){
            throw new ValidacaoException("Curso não encontado");
        }
        if (!usuarioRepository.existsById(dados.autor_id())){
            throw new ValidacaoException("Autor não encontado");
        }
        var jaTemEsseTitulo = topicoRepository.existsByTitulo(dados.titulo());
        var jaTemEssaMensagem = topicoRepository.existsByMensagem(dados.mensagem());

        if (jaTemEsseTitulo || jaTemEssaMensagem){
            throw new ValidacaoException("Já existe esse topico");
        }
    }
}
