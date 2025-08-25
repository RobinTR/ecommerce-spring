package org.robn.ecommerce.auth.service;

import org.robn.ecommerce.auth.model.EcoUser;
import org.robn.ecommerce.auth.model.request.EcoUserCreateRequest;
import org.robn.ecommerce.auth.model.request.EcoUserLoginRequest;

public interface EcoUserService {

    EcoUser register(EcoUserCreateRequest ecoUserCreateRequest);

    EcoUser login(EcoUserLoginRequest ecoUserLoginRequest);

}
