package com.forum.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.forum.model.Usuario;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    @Value("${api.security.token.secret}")
    private String secret;

    private static final String ISSUER = "Forum API";

    public String gerarToken(Usuario usuario){
        try {
            var algoritomo = Algorithm.HMAC256(secret);
            return JWT.create()
                    .withIssuer(ISSUER)
                    .withSubject(usuario.getEmail())
                    .withExpiresAt(dataExpiracao())
                    .sign(algoritomo);
        } catch (JWTCreationException ex){
            throw new RuntimeException("Erro ao gerar o token", ex);
        }

    }

    public String getSubject(String tokenJWT){
        try {
            var algoritomo = Algorithm.HMAC256(secret);
            return JWT.require(algoritomo)
                    .withIssuer(ISSUER)
                    .build()
                    .verify(tokenJWT)
                    .getSubject();
        } catch (JWTVerificationException ex){
            throw new RuntimeException("Token inválido ou expirado!");
        }
    }

    public boolean validateToken(String token) {
        return !getSubject(token).isEmpty();
    }

    private Instant dataExpiracao() {
        return LocalDateTime.now().plusHours(2)
                .toInstant(ZoneOffset.of("-03:00"));
    }

}
