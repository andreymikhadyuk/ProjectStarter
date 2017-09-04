package com.projectstarter.ProjectStarter.security.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.projectstarter.ProjectStarter.security.exception.InvalidTokenAuthenticationException;
import com.projectstarter.ProjectStarter.security.model.TokenPayload;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.jwt.Jwt;
import org.springframework.security.jwt.JwtHelper;
import org.springframework.security.jwt.crypto.sign.MacSigner;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.Instant;
import java.util.Objects;

/**
 * @author ikatlinsky
 * @since 5/12/17
 */
@Component
@RequiredArgsConstructor
public class AuthenticationHelper {

    public static final String AUTHENTICATION_HEADER = "Authorization";
    public static final String AUTHENTICATION_PARAM = "auth";
    private final String SECRET = "ChangeMeToSomethingElse";

    private Long tokenExpirationTime = 3600L;

    private final ObjectMapper objectMapper;

    public String generateToken(final Long userId) {
        try {
            TokenPayload payload = new TokenPayload(
                    userId,
                    Instant.now().getEpochSecond() + this.tokenExpirationTime
            );

            String token = this.objectMapper.writeValueAsString(payload);
            return JwtHelper.encode(token, new MacSigner(SECRET)).getEncoded();
        } catch (JsonProcessingException exception) {
            throw new InternalAuthenticationServiceException("Error generating token.", exception);
        }
    }

    public TokenPayload decodeToken(final String token) {
        if (Objects.isNull(token)) {
            throw new InvalidTokenAuthenticationException("Token was null or blank.");
        }

        // Getting JWT object from string token
        Jwt jwt = JwtHelper.decode(token);

        // Validate token signature (to be sure that token has not been tampered with)
        try {
            jwt.verifySignature(new MacSigner(SECRET));
        } catch (Exception exception) {
            throw new InvalidTokenAuthenticationException("Token signature verification failed.", exception);
        }

        // Getting payload of token
        String claims = jwt.getClaims();
        TokenPayload tokenPayload;
        try {
            tokenPayload = this.objectMapper.readValue(claims, TokenPayload.class);
        } catch (IOException exception) {
            throw new InvalidTokenAuthenticationException("Token parsing failed.", exception);
        }

        return tokenPayload;
    }
}
