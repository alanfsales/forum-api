package com.forum.controller;

import com.forum.dto.in.DadosCadastroCurso;
import com.forum.dto.out.DadosDetalhamentoCurso;
import com.forum.service.CursoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/cursos")
public class CursoController {

    @Autowired
    private CursoService service;

    @GetMapping
    public List<DadosDetalhamentoCurso> listar(){
        return service.listar();
    }

    @GetMapping("/{id}")
    public DadosDetalhamentoCurso buscar(@PathVariable Long id){
        return service.buscar(id);
    }

    @PostMapping
    public ResponseEntity<DadosDetalhamentoCurso> salvar(@RequestBody @Valid DadosCadastroCurso dados,
                                 UriComponentsBuilder uriBuilder){
        DadosDetalhamentoCurso dadosDetalhamentoCurso = service.salvar(dados);

        var uri = uriBuilder.path("/cursos/{id}")
                .buildAndExpand(dadosDetalhamentoCurso.id()).toUri();

        return ResponseEntity.created(uri).body(dadosDetalhamentoCurso);
    }

    @PutMapping("/{id}")
    public DadosDetalhamentoCurso atualizar(
            @PathVariable Long id,
            @RequestBody @Valid DadosCadastroCurso dados){

        return service.atualizar(id, dados);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable Long id){
        service.excluir(id);
        return ResponseEntity.noContent().build();
    }
}
