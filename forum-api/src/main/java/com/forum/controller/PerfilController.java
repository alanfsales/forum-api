package com.forum.controller;

import com.forum.dto.in.DadosCadastroPerfil;
import com.forum.model.Perfil;
import com.forum.service.PerfilService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/perfis")
public class PerfilController {

    @Autowired
    private PerfilService perfilService;

    @GetMapping
    public List<Perfil> listar(){
        return perfilService.listar();
    }

    @GetMapping("/{id}")
    public Perfil buscar(@PathVariable Long id){
        return perfilService.buscar(id);
    }

    @PostMapping
    public ResponseEntity salvar(@RequestBody DadosCadastroPerfil dados,
                                 UriComponentsBuilder uriBuilder){
        Perfil perfil = perfilService.salvar(dados);

        URI uri = uriBuilder.path("/perfis/{id}").buildAndExpand(perfil.getId()).toUri();

        return ResponseEntity.created(uri).body(perfil);
    }

    @PutMapping("/{id}")
    public ResponseEntity atualizar(@PathVariable Long id,
                                    @RequestBody @Valid DadosCadastroPerfil dados){
        return ResponseEntity.ok(perfilService.atualizar(id, dados));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity remover(@PathVariable Long id){
        perfilService.remover(id);
        return ResponseEntity.noContent().build();
    }
}
