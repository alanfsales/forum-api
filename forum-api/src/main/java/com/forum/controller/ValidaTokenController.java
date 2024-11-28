package com.forum.controller;

import com.forum.security.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/validate-token")
public class ValidaTokenController {

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity<?> validateToken(@RequestHeader("Authorization") String authorization) {
        try {
            String token = authorization.replace("Bearer ", "");
            // Valide o token aqui (usando JWT, por exemplo)
            boolean isValid = tokenService.validateToken(token); // Implementação do serviço de validação

            if (isValid) {
                return ResponseEntity.ok().body("Token válido.");
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token inválido ou expirado.");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Erro ao validar o token.");
        }
    }
}
