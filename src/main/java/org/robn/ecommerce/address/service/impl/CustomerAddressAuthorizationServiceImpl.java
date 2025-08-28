package org.robn.ecommerce.address.service.impl;

import org.robn.ecommerce.address.service.CustomerAddressAuthorizationService;
import org.robn.ecommerce.auth.exception.EcoAccessDeniedException;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CustomerAddressAuthorizationServiceImpl implements CustomerAddressAuthorizationService {

    @Override
    public void checkAccess(final UUID currentUserId, final boolean isAdmin, final UUID targetCustomerId) {
        if (!(isAdmin || targetCustomerId.equals(currentUserId))) {
            throw EcoAccessDeniedException.of();
        }
    }

}
