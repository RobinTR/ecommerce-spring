package org.robn.ecommerce.auth.port;

import org.robn.ecommerce.auth.model.EcoRefreshToken;

import java.util.List;

public interface RefreshTokenSavePort {

    EcoRefreshToken save(EcoRefreshToken ecoRefreshToken);

    List<EcoRefreshToken> saveAll(List<EcoRefreshToken> tokens);

}
