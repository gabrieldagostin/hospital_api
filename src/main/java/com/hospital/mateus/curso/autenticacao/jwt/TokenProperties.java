package com.hospital.mateus.curso.autenticacao.jwt;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@ConfigurationProperties(prefix = "api.security.token")
@Component
public class TokenProperties {

    private String secret;

}

