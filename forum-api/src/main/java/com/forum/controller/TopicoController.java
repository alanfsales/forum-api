package com.forum.controller;

import com.forum.dto.in.DadosCadastroTopico;
import com.forum.dto.out.DadosDetalhamentoTopico;
import com.forum.service.TopicoService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/topicos")
@SecurityRequirement(name = "bearer-key")
public class TopicoController {

    @Autowired
    private TopicoService topicoService;

    @PostMapping
    @Transactional
    public ResponseEntity<DadosDetalhamentoTopico> salvar(@RequestHeader("Authorization") String token,
                                                          @RequestBody @Valid DadosCadastroTopico dados,
                                                          UriComponentsBuilder uriBuilder) {

        DadosDetalhamentoTopico dadosDetalhamentoTopico = topicoService.salvar(dados, token);

        var uri = uriBuilder.path("/topicos/{id}").buildAndExpand(
                dadosDetalhamentoTopico.id()).toUri();

        return ResponseEntity.created(uri).body(dadosDetalhamentoTopico);
    }

    @GetMapping("/{id}")
    public DadosDetalhamentoTopico buscar(@PathVariable Long id){
        return topicoService.buscar(id);
    }

    @GetMapping
    public List<DadosDetalhamentoTopico> listar(){
        return topicoService.listar();
    }

    @PutMapping("/{id}")
    @Transactional
    public DadosDetalhamentoTopico atualizar(@RequestHeader("Authorization") String token,
                                    @PathVariable Long id,
                                    @RequestBody @Valid DadosCadastroTopico dados){
        return topicoService.atualizar(id, dados, token);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> remover(@RequestHeader("Authorization") String token,
                                  @PathVariable Long id){
        topicoService.remover(id, token);
        return ResponseEntity.noContent().build();
    }
}
