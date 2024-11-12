package com.forum.service;

import com.forum.dto.in.DadosCadastroTopico;
import com.forum.dto.convert.TopicoConvertDTO;
import com.forum.dto.out.DadosDetalhamentoTopico;
import com.forum.exception.RecursoNaoEncontradoException;
import com.forum.exception.UsuarioSemPerimssaoException;
import com.forum.exception.ValidacaoException;
import com.forum.model.Curso;
import com.forum.model.Topico;
import com.forum.model.Usuario;
import com.forum.repository.TopicoRepository;
import com.forum.security.TokenService;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
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
    public DadosDetalhamentoTopico salvar(DadosCadastroTopico dados, String token){
        validarDados(dados);
        Usuario autor = pegarAutorPeloToken(token);
        Curso curso = cursoService.buscarEntidade(dados.cursoId());

        Topico topico = topicoConvertDTO.paraEntidade(dados, autor, curso);

        return new DadosDetalhamentoTopico(topicoRepository.save(topico));
    }

    public DadosDetalhamentoTopico buscar(Long id){
        Topico topico = buscarEntidade(id);
        return topicoConvertDTO.paraDTO(topico);
    }

    public Topico buscarEntidade(Long id){
        return topicoRepository.findById(id).orElseThrow(()->
                new RecursoNaoEncontradoException("Tópico não encontrado"));
    }

    public List<DadosDetalhamentoTopico> listar(){
         List<Topico> topicos = topicoRepository.findAllByOrderByDataCriacaoDesc();
         return topicoConvertDTO.paraDTO(topicos);
    }

    @Transactional
    public DadosDetalhamentoTopico atualizar(Long id, DadosCadastroTopico dados, String token) {
        Topico topicoBD = buscarEntidade(id);
        Usuario autor = pegarAutorPeloToken(token);

        if (!autor.equals(topicoBD.getAutor())){
            throw new UsuarioSemPerimssaoException("Usuario sem permissão");
        }
        validarDados(dados);
        Curso curso = cursoService.buscarEntidade(dados.cursoId());

        Topico topicoDadosNovo = topicoConvertDTO.paraEntidade(dados, autor,curso);
        BeanUtils.copyProperties(topicoDadosNovo, topicoBD, "id");
        return topicoConvertDTO.paraDTO(topicoBD);
    }

    @Transactional
    public void remover(Long id, String token) {
        Topico topico = buscarEntidade(id);
        Usuario autor = pegarAutorPeloToken(token);

        if (!autor.equals(topico.getAutor())){
            throw new UsuarioSemPerimssaoException("Usuario sem permissão");
        }

        topicoRepository.deleteById(id);
    }

    private void validarDados(DadosCadastroTopico dados) {
        cursoService.buscar(dados.cursoId());

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
