//package com.drkapps.shared.infrastructure.interceptor;
//
//import com.drkapps.shared.infrastructure.dto.ResponseGlobalDto;
//import io.jsonwebtoken.Claims;
//import io.jsonwebtoken.JwtException;
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.SignatureAlgorithm;
//import jakarta.annotation.Priority;
//import jakarta.inject.Inject;
//import org.eclipse.microprofile.config.inject.ConfigProperty;
//import jakarta.ws.rs.Priorities;
//import jakarta.ws.rs.container.*;
//
//import jakarta.ws.rs.core.*;
//import jakarta.ws.rs.ext.Provider;
//
//
//import java.io.IOException;
//import java.security.Key;
//import javax.crypto.spec.SecretKeySpec;
//
//@Provider
//@Priority(Priorities.AUTHENTICATION)
//public class JwtAuthFilter implements ContainerRequestFilter {
//
//    @Inject
//    @ConfigProperty(name = "jwt.secret")
//    String secretKey;
//    private static final String HEADER_PREFIX = "Bearer ";
//
//    @Override
//    public void filter(ContainerRequestContext requestContext) throws IOException {
//        String authHeader = requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);
//
//        if (authHeader == null || !authHeader.startsWith(HEADER_PREFIX)) {
//            var respErr = ResponseGlobalDto.error("no token provided", 401);
//            abort(requestContext, respErr);
//            return;
//        }
//
//        String token = authHeader.substring(HEADER_PREFIX.length()).trim();
//
//        try {
//            Claims claims = validarToken(token);
//            // Aquí podrías guardar los claims en el contexto si lo necesitas
//            // requestContext.setProperty("user", claims.getSubject());
//        } catch (JwtException | IllegalArgumentException e) {
//            var respErr = ResponseGlobalDto.error("error validation " + e.getMessage(), 401);
//            abort(requestContext, respErr);
//        }
//    }
//
//    private Claims validarToken(String token) {
//        byte[] secretBytes = secretKey.getBytes();
//        Key key = new SecretKeySpec(secretBytes, SignatureAlgorithm.HS256.getJcaName());
//
//        return Jwts.parserBuilder()
//                .setSigningKey(key)
//                .build()
//                .parseClaimsJws(token)
//                .getBody();
//    }
//
//    private void abort(ContainerRequestContext context, ResponseGlobalDto resp) {
//        context.abortWith(
//                Response.status(Response.Status.UNAUTHORIZED)
//                        .entity(resp)
//                        .build()
//        );
//    }
//}
