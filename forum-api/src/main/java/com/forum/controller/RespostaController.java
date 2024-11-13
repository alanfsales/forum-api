package com.forum.controller;

import com.forum.dto.in.DadosCadastroResposta;
import com.forum.dto.out.DadosDetalhamentoResposta;
import com.forum.model.Resposta;
import com.forum.service.RespostaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/respostas")
public class RespostaController {

    @Autowired
    private RespostaService respostaService;

    @GetMapping
    public List<DadosDetalhamentoResposta> listar(){
        return respostaService.listar();
    }

    @GetMapping("/{id}")
    public DadosDetalhamentoResposta buscar(@PathVariable Long id){
        return respostaService.buscar(id);
    }

    @PostMapping
    public ResponseEntity<DadosDetalhamentoResposta> salvar(@RequestBody DadosCadastroResposta dados,
                                                           @RequestHeader("Authorization") String token,
                                                           UriComponentsBuilder uriBuilder){

        DadosDetalhamentoResposta dadosDetalhamentoResposta = respostaService.salvar(dados, token);

        URI uri = uriBuilder.path("/respostas/{id}")
                .buildAndExpand(dadosDetalhamentoResposta.id()).toUri();

        return ResponseEntity.created(uri).body(dadosDetalhamentoResposta);
    }

    @PutMapping("/{id}")
    public DadosDetalhamentoResposta alterar(@PathVariable Long id,
                            @RequestBody DadosCadastroResposta dados,
                            @RequestHeader("Authorization") String token){
        return respostaService.alterar(id, dados, token);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable Long id,
                            @RequestHeader("Authorization") String token){
        respostaService.remover(id, token);
        return ResponseEntity.noContent().build();
    }

}
