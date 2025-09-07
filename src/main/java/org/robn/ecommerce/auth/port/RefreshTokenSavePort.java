package org.robn.ecommerce.auth.port;

import org.robn.ecommerce.auth.model.RefreshToken;

public interface RefreshTokenSavePort {

    RefreshToken save(RefreshToken refreshToken);

}
