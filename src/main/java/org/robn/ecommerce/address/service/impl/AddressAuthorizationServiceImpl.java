package org.robn.ecommerce.address.service.impl;

import lombok.RequiredArgsConstructor;
import org.robn.ecommerce.address.service.AddressAuthorizationService;
import org.robn.ecommerce.auth.exception.EcoAccessDeniedException;
import org.robn.ecommerce.auth.util.EcoSecurityUtil;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AddressAuthorizationServiceImpl implements AddressAuthorizationService {

    @Override
    public void checkAccessForCurrentUser(final UUID targetUserId) {
        final UUID currentUserId = EcoSecurityUtil.getCurrentUserId();

        if (!targetUserId.equals(currentUserId)) {
            throw EcoAccessDeniedException.of();
        }
    }

}
