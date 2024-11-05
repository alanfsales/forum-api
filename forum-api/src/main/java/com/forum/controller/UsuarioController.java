package com.forum.controller;

import com.forum.dto.DadosCadastroUsuario;
import com.forum.dto.DadosDetalhamentoUsuario;
import com.forum.dto.convert.UsuarioConvertDTO;
import com.forum.model.Usuario;
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

    @Autowired
    private UsuarioConvertDTO usuarioConvertDTO;

    @GetMapping
    private List<DadosDetalhamentoUsuario> listar(){
        return usuarioConvertDTO.paraDTO(usuarioService.listar());
    }

    @GetMapping("/{id}")
    public DadosDetalhamentoUsuario buscar(@PathVariable Long id){
        return usuarioConvertDTO.paraTDO(usuarioService.buscar(id));
    }

    @PostMapping
    public ResponseEntity salvar(@RequestBody @Valid DadosCadastroUsuario dados,
                                 UriComponentsBuilder uriBuilder){
        Usuario usuario = usuarioService.salvar(usuarioConvertDTO.paraUsuario(dados));

        URI uri = uriBuilder.path("/usuarios/{id}")
                .buildAndExpand(usuario.getId()).toUri();

        return ResponseEntity.created(uri).body(usuarioConvertDTO.paraTDO(usuario));
    }

    @PutMapping("/{id}")
    public ResponseEntity atualizar(@PathVariable Long id,
            @RequestBody DadosCadastroUsuario dados){
        Usuario usuario = usuarioConvertDTO.paraUsuario(dados);

        DadosDetalhamentoUsuario dadosDetalhamentoUsuario =
                new DadosDetalhamentoUsuario(usuarioService.atualizar(id, usuario));
        return ResponseEntity.ok(dadosDetalhamentoUsuario);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity excluir(@PathVariable Long id){
        usuarioService.excluir(id);
        return  ResponseEntity.noContent().build();
    }
}
