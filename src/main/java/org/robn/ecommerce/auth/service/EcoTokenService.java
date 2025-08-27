package org.robn.ecommerce.auth.service;

import io.jsonwebtoken.Claims;
import org.robn.ecommerce.auth.model.EcoToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import java.util.UUID;

public interface EcoTokenService {

    EcoToken generateToken(Claims claims);

    EcoToken generateToken(Claims claims, String refreshToken);

    Claims getPayload(String jwt);

    UUID getUserId(String token);

    void verifyAndValidate(String token);

    UsernamePasswordAuthenticationToken getAuthentication(String token);
}
