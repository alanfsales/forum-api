package com.forum.service;

import com.forum.dto.DadosCadastroTopico;
import com.forum.dto.convert.TopicoConvertDTO;
import com.forum.exception.RecursoNaoEncontradoException;
import com.forum.exception.UsuarioSemPerimssaoException;
import com.forum.exception.ValidacaoException;
import com.forum.model.Curso;
import com.forum.model.Topico;
import com.forum.model.Usuario;
import com.forum.repository.TopicoRepository;
import com.forum.security.TokenService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TopicoService {

    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private CursoService cursoService;

    @Autowired
    private TopicoConvertDTO topicoConvertDTO;

    @Autowired
    private TokenService tokenService;

    @Transactional
    public Topico salvar(DadosCadastroTopico dados, String token){
        validarDados(dados);
        Usuario autor = pegarAutorPeloToken(token);

        Topico topico = topicoConvertDTO.paraTopico(dados, autor);

        return topicoRepository.save(topico);
    }

    public Topico buscar(Long id){
        return topicoRepository.findById(id).orElseThrow(()->
                new RecursoNaoEncontradoException("Tópico não encontrado"));
    }

    public List<Topico> listar(){
        return topicoRepository.findAllByOrderByDataCriacaoDesc();
    }

    @Transactional
    public Topico atualizar(Long id, DadosCadastroTopico dados, String token) {
        Topico topico = buscar(id);
        Usuario autor = pegarAutorPeloToken(token);
        Curso curso = cursoService.buscar(dados.curso_id());

        if (!autor.equals(topico.getAutor())){
            throw new UsuarioSemPerimssaoException("Usuario sem permissão");
        }

        validarDados(dados);

        topico.setTitulo(dados.titulo());
        topico.setMensagem(dados.mensagem());
        topico.setCurso(curso);

        return topico;
    }

    @Transactional
    public void remover(Long id, String token) {
        Topico topico = buscar(id);
        Usuario autor = pegarAutorPeloToken(token);

        if (!autor.equals(topico.getAutor())){
            throw new UsuarioSemPerimssaoException("Usuario sem permissão");
        }

        topicoRepository.deleteById(id);
    }

    private void validarDados(DadosCadastroTopico dados) {
        cursoService.buscar(dados.curso_id());

        var jaTemEsseTitulo = topicoRepository.existsByTitulo(dados.titulo());
        var jaTemEssaMensagem = topicoRepository.existsByMensagem(dados.mensagem());

        if (jaTemEsseTitulo || jaTemEssaMensagem){
            throw new ValidacaoException("Já existe esse topico");
        }
    }

    private Usuario pegarAutorPeloToken(String token){
        String email = tokenService.getSubject(token.replace("Bearer ", "").trim());
        return usuarioService.buscarPorEmail(email);
    }

}
