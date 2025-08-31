package org.robn.ecommerce.auth.service.impl;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.time.DateUtils;
import org.robn.ecommerce.auth.config.EcoAuthConfiguration;
import org.robn.ecommerce.auth.exception.EcoTokenInvalidException;
import org.robn.ecommerce.auth.model.EcoToken;
import org.robn.ecommerce.auth.service.EcoTokenService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EcoTokenServiceImpl implements EcoTokenService {

    private final EcoAuthConfiguration ecoAuthConfiguration;

    @Override
    public EcoToken generateToken(final Claims claims) {
        final long currentTimeMillis = System.currentTimeMillis();

        final Date accessTokenExpiresAt = DateUtils.addMinutes(new Date(), ecoAuthConfiguration.getAccessTokenExpireMinute());

        final String accessToken = createJwtBuilder(currentTimeMillis)
                .claims(claims)
                .id(UUID.randomUUID().toString())
                .subject(claims.getSubject())
                .expiration(accessTokenExpiresAt)
                .compact();

        return EcoToken.builder()
                .accessToken(accessToken)
                .build();
    }

    @Override
    public EcoToken generateToken(final Claims claims, final String refreshToken) {
        return null;
    }

    @Override
    public Claims getPayload(final String token) {
        try {
            return Jwts.parser()
                    .verifyWith(getKey())
                    .build()
                    .parseSignedClaims(token)
                    .getPayload();
        } catch (JwtException exception) {
            throw EcoTokenInvalidException.of(token, exception);
        }

    }

    @Override
    public UUID getUserId(final String token) {
        final Claims claims = getPayload(token);
        final String userId = claims.get("userId", String.class);

        return UUID.fromString(userId);
    }

    @Override
    public void verifyAndValidate(final String token) {
        if (token == null || token.isBlank()) {
            throw EcoTokenInvalidException.of(token);
        }

        try {
            Jwts.parser()
                    .verifyWith(getKey())
                    .build()
                    .parseSignedClaims(token);
        } catch (JwtException exception) {
            throw EcoTokenInvalidException.of(token, exception);
        }

    }

    @Override
    public UsernamePasswordAuthenticationToken getAuthentication(final String token) {
        final Claims claims = getPayload(token);
        final UUID userId = getUserId(token);
        final List<String> roles = claims.get("roles", List.class);
        final List<SimpleGrantedAuthority> authorities = roles == null
                ? List.of()
                : roles.stream().map(SimpleGrantedAuthority::new).toList();

        return new UsernamePasswordAuthenticationToken(userId, null, authorities);
    }

    private SecretKey getKey() {
        final byte[] keyBytes = Decoders.BASE64.decode(ecoAuthConfiguration.getSecretKey());

        return Keys.hmacShaKeyFor(keyBytes);
    }

    private JwtBuilder createJwtBuilder(final long currentTimeMillis) {
        return Jwts.builder()
                .issuer(ecoAuthConfiguration.getTokenIssuer())
                .issuedAt(new Date(currentTimeMillis))
                .signWith(getKey());
    }

}
