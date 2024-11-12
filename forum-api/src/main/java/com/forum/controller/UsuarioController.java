package com.forum.controller;

import com.forum.dto.in.DadosCadastroUsuario;
import com.forum.dto.out.DadosDetalhamentoUsuario;
import com.forum.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    private List<DadosDetalhamentoUsuario> listar(){
        return usuarioService.listar();
    }

    @GetMapping("/{id}")
    public DadosDetalhamentoUsuario buscar(@PathVariable Long id){
        return usuarioService.buscar(id);
    }

    @PostMapping
    public ResponseEntity<DadosDetalhamentoUsuario> salvar(@RequestBody @Valid DadosCadastroUsuario dados,
                                 UriComponentsBuilder uriBuilder){
        DadosDetalhamentoUsuario dadosDetalhamentoUsuario = usuarioService.salvar(dados);

        URI uri = uriBuilder.path("/usuarios/{id}")
                .buildAndExpand(dadosDetalhamentoUsuario.id()).toUri();

        return ResponseEntity.created(uri).body(dadosDetalhamentoUsuario);
    }

    @PutMapping("/{id}")
    public DadosDetalhamentoUsuario atualizar(@PathVariable Long id,
            @RequestBody DadosCadastroUsuario dados){
        return usuarioService.atualizar(id, dados);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id){
        usuarioService.excluir(id);
        return  ResponseEntity.noContent().build();
    }
}
