package com.hospital.mateus.curso.autenticacao.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.hospital.mateus.curso.usuario.model.Usuario;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@RequiredArgsConstructor
@Service
public class TokenService {

    private final TokenProperties tokenProperties;

    public String gerarToken(Usuario usuario) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(tokenProperties.getSecret());
            return JWT.create()
                    .withIssuer("hospital_api")
                    .withSubject(usuario.getLogin())
                    .withExpiresAt(dataExpiracao())
                    .sign(algorithm);
        } catch (JWTCreationException exception) {
            throw new RuntimeException("Erro ao gerar token", exception);
        }
    }

    public String getSubject(String TokenJWT) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(tokenProperties.getSecret());
            return JWT.require(algorithm)
                    .withIssuer("hospital_api")
                    .build()
                    .verify(TokenJWT)
                    .getSubject();
        } catch (JWTVerificationException exception) {
            throw new RuntimeException("Token inválido ou expirado");
        }
    }

    private Instant dataExpiracao() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }

}
