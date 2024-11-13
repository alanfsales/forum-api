package com.forum.service;

import com.forum.dto.convert.RespostaConvertDTO;
import com.forum.dto.in.DadosCadastroResposta;
import com.forum.dto.out.DadosDetalhamentoResposta;
import com.forum.exception.RecursoNaoEncontradoException;
import com.forum.exception.UsuarioSemPerimssaoException;
import com.forum.model.Resposta;
import com.forum.model.Topico;
import com.forum.model.Usuario;
import com.forum.repository.RespostaRepository;
import com.forum.security.TokenService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RespostaService {

    @Autowired
    private RespostaRepository respostaRepository;

    @Autowired
    private TopicoService topicoService;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private RespostaConvertDTO respostaConvertDTO;

    @Autowired
    private TokenService tokenService;

    public List<DadosDetalhamentoResposta> listar(){
        return respostaConvertDTO.paraDTO(respostaRepository.findAll());
    }

    public DadosDetalhamentoResposta buscar(Long id) {
        return respostaConvertDTO.paraTDO(buscarEntidade(id));
    }

    public Resposta buscarEntidade(Long id) {
        return respostaRepository.findById(id).orElseThrow(() ->
                new RecursoNaoEncontradoException("Respota não encontrada"));
    }

    @Transactional
    public DadosDetalhamentoResposta salvar(DadosCadastroResposta dados, String token) {
        Topico topico = topicoService.buscarEntidade(dados.topicoId());
        Usuario autor = pegarAutorPeloToken(token);

        Resposta resposta = new Resposta(dados, topico, autor);
        return respostaConvertDTO.paraTDO(respostaRepository.save(resposta));
    }

    @Transactional
    public DadosDetalhamentoResposta alterar(Long id, DadosCadastroResposta dados, String token){
        Resposta respostaBD = buscarEntidade(id);
        temPermissao(token, respostaBD);
        respostaBD.setMensagem(dados.mensagem());

        return respostaConvertDTO.paraTDO(respostaBD);
    }

    @Transactional
    public void remover(Long id, String token){
        Resposta resposta = buscarEntidade(id);
        temPermissao(token, resposta);
        respostaRepository.deleteById(id);
    }

    private void temPermissao(String token, Resposta resposta) {
        Usuario autorResposta = usuarioService.buscarEntidade(resposta.getAutor().getId());
        Usuario autorToken = pegarAutorPeloToken(token);

        if (!autorResposta.equals(autorToken)){
            throw new UsuarioSemPerimssaoException("Usuário sem permissão para alterar essa resposta");
        }
    }

    private Usuario pegarAutorPeloToken(String token){
        String email = tokenService.getSubject(token.replace("Bearer ", "").trim());
        return usuarioService.buscarPorEmail(email);
    }

}
