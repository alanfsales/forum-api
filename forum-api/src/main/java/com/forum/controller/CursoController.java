package com.forum.controller;

import com.forum.dto.DadosCadastroCurso;
import com.forum.model.Curso;
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
    public List<Curso> listar(){
        return service.listar();
    }

    @GetMapping("/{id}")
    public Curso buscar(@PathVariable Long id){
        return service.buscar(id);
    }

    @PostMapping
    public ResponseEntity salvar(@RequestBody @Valid DadosCadastroCurso dados,
                                 UriComponentsBuilder uriBuilder){
        var curso = service.salvar(new Curso(dados.nome(), dados.categoria()));

        var uri = uriBuilder.path("/cursos/{id}").buildAndExpand(curso.getId()).toUri();

        return ResponseEntity.created(uri).body(curso);
    }

    @PutMapping("/{id}")
    public Curso atualizar(
            @PathVariable Long id,
            @RequestBody @Valid DadosCadastroCurso dados){

        return service.atualizar(id, dados);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity excluir(@PathVariable Long id){
        service.excluir(id);
        return ResponseEntity.noContent().build();
    }
}
