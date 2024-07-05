package com.forum.controller;

import com.forum.dto.DadosCadastroTopico;
import com.forum.service.TopicoService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/topicos")
@SecurityRequirement(name = "bearer-key")
public class TopicoController {

    @Autowired
    private TopicoService topicoService;

    @PostMapping
    @Transactional
    public ResponseEntity salvar(@RequestHeader("Authorization") String token,
                                 @RequestBody @Valid DadosCadastroTopico dados,
                                 UriComponentsBuilder uriBuilder) {

        var dadosDetalhamentoTopico = topicoService.salvar(dados, token);
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
    public ResponseEntity atualizar(@RequestHeader("Authorization") String token,
                                    @PathVariable Long id,
                                    @RequestBody @Valid DadosCadastroTopico dados){
        var dadosDetalhamentoTopicos = topicoService.atualizar(id, dados, token);

        return ResponseEntity.ok(dadosDetalhamentoTopicos);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity remover(@RequestHeader("Authorization") String token,
                                  @PathVariable Long id){
        topicoService.remover(id, token);
        return ResponseEntity.noContent().build();
    }
}
