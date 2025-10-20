package org.robn.ecommerce.auth.service.impl;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.time.DateUtils;
import org.robn.ecommerce.auth.config.EcoAuthConfiguration;
import org.robn.ecommerce.auth.exception.EcoInvalidTokenException;
import org.robn.ecommerce.auth.model.EcoRefreshToken;
import org.robn.ecommerce.auth.model.EcoToken;
import org.robn.ecommerce.auth.service.EcoRefreshTokenService;
import org.robn.ecommerce.auth.service.EcoTokenService;
import org.robn.ecommerce.auth.util.CryptoUtil;
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
    private final EcoRefreshTokenService ecoRefreshTokenService;

    @Override
    public EcoToken generateToken(final Claims claims, String deviceId) {
        final String accessToken = this.initializeToken(claims);
        final EcoRefreshToken ecoRefreshToken = ecoRefreshTokenService.generateRefreshToken(getUserId(accessToken), deviceId);

        return EcoToken.builder()
                .accessToken(accessToken)
                .refreshToken(ecoRefreshToken.getToken())
                .build();
    }

    @Override
    public EcoToken generateToken(final Claims claims, final String refreshToken, String deviceId) {
        final String accessToken = this.initializeToken(claims);
        final EcoRefreshToken newEcoRefreshToken = ecoRefreshTokenService.rotate(refreshToken, getUserId(accessToken), deviceId);

        return EcoToken.builder()
                .accessToken(accessToken)
                .refreshToken(newEcoRefreshToken.getToken())
                .build();
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
            throw EcoInvalidTokenException.of(token, exception);
        }

    }

    @Override
    public UUID getUserId(final String token) {
        final Claims claims = this.getPayload(token);
        final String userId = claims.get("userId", String.class);

        return UUID.fromString(userId);
    }

    @Override
    public void verifyAndValidate(final String token) {
        if (token == null || token.isBlank()) {
            throw EcoInvalidTokenException.of(token);
        }

        try {
            Jwts.parser()
                    .verifyWith(getKey())
                    .build()
                    .parseSignedClaims(token);
        } catch (JwtException exception) {
            throw EcoInvalidTokenException.of(token, exception);
        }

    }

    @Override
    public UsernamePasswordAuthenticationToken getAuthentication(final String token) {
        final Claims claims = this.getPayload(token);
        final UUID userId = this.getUserId(token);
        final List<String> roles = claims.get("roles", List.class);
        final List<SimpleGrantedAuthority> authorities = roles == null
                ? List.of()
                : roles.stream().map(SimpleGrantedAuthority::new).toList();

        return new UsernamePasswordAuthenticationToken(userId, null, authorities);
    }

    private SecretKey getKey() {
        return CryptoUtil.getSecretKey(ecoAuthConfiguration.getSecretKey());
    }

    private JwtBuilder createJwtBuilder(final long currentTimeMillis) {
        return Jwts.builder()
                .issuer(ecoAuthConfiguration.getTokenIssuer())
                .issuedAt(new Date(currentTimeMillis))
                .signWith(getKey());
    }

    private String initializeToken(final Claims claims) {
        final long currentTimeMillis = System.currentTimeMillis();
        final Date accessTokenExpiresAt = DateUtils.addMinutes(new Date(), ecoAuthConfiguration.getAccessTokenExpireMinute());

        return createJwtBuilder(currentTimeMillis)
                .id(UUID.randomUUID().toString())
                .claims(claims)
                .subject(claims.getSubject())
                .expiration(accessTokenExpiresAt)
                .compact();
    }

}
