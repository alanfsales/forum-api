package com.forum.controller;

import com.forum.dto.DadosCadastroTopico;
import com.forum.service.TopicoService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/topicos")
public class TopicoController {

    @Autowired
    private TopicoService topicoService;

    @PostMapping
    @Transactional
    public ResponseEntity salvar(@RequestBody @Valid DadosCadastroTopico dados, UriComponentsBuilder uriBuilder) {
        var dadosDetalhamentoTopico = topicoService.salvar(dados);
        var uri = uriBuilder.path("/topicos/{id}").buildAndExpand(dadosDetalhamentoTopico.id()).toUri();

        return ResponseEntity.created(uri).body(dadosDetalhamentoTopico);
    }

    @GetMapping("/{id}")
    public ResponseEntity buscar(@PathVariable Long id){
        var dadosDetalhamentoTopico = topicoService.buscar(id);
        return ResponseEntity.ok(dadosDetalhamentoTopico);
    }

    @GetMapping
    public ResponseEntity listar(){
        var dadosDetalhamentoTopicos = topicoService.listar();
        return ResponseEntity.ok(dadosDetalhamentoTopicos);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity atualizar(@PathVariable Long id,
                                    @RequestBody @Valid DadosCadastroTopico dados){
        var dadosDetalhamentoTopicos = topicoService.atualizar(id, dados);

        return ResponseEntity.ok(dadosDetalhamentoTopicos);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity remover(@PathVariable Long id){
        topicoService.remover(id);
        return ResponseEntity.noContent().build();
    }
}
