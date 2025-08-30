package org.robn.ecommerce.auth.service;

import org.robn.ecommerce.auth.model.EcoToken;
import org.robn.ecommerce.auth.model.request.EcoUserCreateRequest;
import org.robn.ecommerce.auth.model.request.EcoUserLoginRequest;

public interface EcoAuthService {

    EcoToken register(EcoUserCreateRequest ecoUserCreateRequest);

    EcoToken login(EcoUserLoginRequest ecoUserLoginRequest);

}
