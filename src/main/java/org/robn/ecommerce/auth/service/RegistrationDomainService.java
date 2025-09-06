package org.robn.ecommerce.auth.service;

import org.robn.ecommerce.auth.model.EcoUser;
import org.robn.ecommerce.auth.model.enums.Role;

public interface RegistrationDomainService {

    void prepareForRegistration(EcoUser user, Role role);

}
