package com.drkapps.shared.infrastructure.port;

public interface IJwtService {

    String generateAccessToken(String subject, String email, String role);

    String generateRefreshToken(String subject);

}
