package com.drkapps.shared.infrastructure.service;

import com.drkapps.shared.infrastructure.port.IJwtService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.time.Instant;
import java.util.Date;
import java.util.UUID;

@ApplicationScoped
public class JwtService implements IJwtService {

    @Inject
    @ConfigProperty(name = "jwt.secret")
    String secret;

    @Inject
    @ConfigProperty(name = "jwt.secret.refresh")
    String secretRefresh;

    private static final Long EXPIRES_IN_MINUTES = 60L;
    private static final Long REFRESH_EXPIRES_IN_MINUTES = 43200L;

    @Override
    public String generateAccessToken(String subject, String email, String role) {
        return buildToken(subject, email, role);
    }

    @Override
    public String generateRefreshToken(String subject) {
        return Jwts.builder()
                .setSubject(subject)
                .setId(UUID.randomUUID().toString())
                .setIssuedAt(new Date())
                .setExpiration(Date.from(Instant.now().plusSeconds(REFRESH_EXPIRES_IN_MINUTES * 60)))
                .signWith(getSigningKeyRefresh(), SignatureAlgorithm.HS256)
                .compact();
    }

    private String buildToken(String subject, String email, String role) {
        return Jwts.builder()
                .setSubject(subject)
                .claim("email", email)
                .claim("role", role)
                .setIssuedAt(new Date())
                .setExpiration(Date.from(Instant.now().plusSeconds(EXPIRES_IN_MINUTES * 60)))
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    private Key getSigningKey() {
        byte[] keyBytes = secret.getBytes();
        return new SecretKeySpec(keyBytes, SignatureAlgorithm.HS256.getJcaName());
    }

    private Key getSigningKeyRefresh() {
        byte[] keyBytes = secretRefresh.getBytes();
        return new SecretKeySpec(keyBytes, SignatureAlgorithm.HS256.getJcaName());
    }


}
