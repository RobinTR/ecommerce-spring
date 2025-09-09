package org.robn.ecommerce.auth.port;

import org.robn.ecommerce.auth.model.RefreshToken;

import java.util.List;

public interface RefreshTokenSavePort {

    RefreshToken save(RefreshToken refreshToken);

    List<RefreshToken> saveAll(List<RefreshToken> tokens);

}
