package org.robn.ecommerce.address.service.impl;

import org.robn.ecommerce.address.service.AddressAuthorizationService;
import org.robn.ecommerce.auth.exception.EcoAccessDeniedException;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AddressAuthorizationServiceImpl implements AddressAuthorizationService {

    @Override
    public void checkAccess(final UUID currentUserId, final boolean isAdmin, final UUID targetUserId) {
        if (!(isAdmin || targetUserId.equals(currentUserId))) {
            throw EcoAccessDeniedException.of();
        }
    }

}
