package com.forum.controller;

import com.forum.dto.DadosCadastroTopico;
import com.forum.dto.convert.TopicoConvertDTO;
import com.forum.model.Topico;
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

    @Autowired
    private TopicoConvertDTO topicoConvertDTO;

    @PostMapping
    @Transactional
    public ResponseEntity salvar(@RequestHeader("Authorization") String token,
                                 @RequestBody @Valid DadosCadastroTopico dados,
                                 UriComponentsBuilder uriBuilder) {

        Topico topico = topicoService.salvar(dados, token);

        var uri = uriBuilder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();

        return ResponseEntity.created(uri).body(topicoConvertDTO.paraDTO(topico));
    }

    @GetMapping("/{id}")
    public ResponseEntity buscar(@PathVariable Long id){
        Topico topico = topicoService.buscar(id);
        return ResponseEntity.ok(topicoConvertDTO.paraDTO(topico));
    }

    @GetMapping
    public ResponseEntity listar(){
        List<Topico> topicos = topicoService.listar();
        return ResponseEntity.ok(topicoConvertDTO.paraDTO(topicos));
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity atualizar(@RequestHeader("Authorization") String token,
                                    @PathVariable Long id,
                                    @RequestBody @Valid DadosCadastroTopico dados){
        Topico topico = topicoService.atualizar(id, dados, token);
        return ResponseEntity.ok(topicoConvertDTO.paraDTO(topico));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity remover(@RequestHeader("Authorization") String token,
                                  @PathVariable Long id){
        topicoService.remover(id, token);
        return ResponseEntity.noContent().build();
    }
}
