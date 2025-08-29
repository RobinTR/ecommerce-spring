package org.robn.ecommerce.address.service;

import java.util.UUID;

public interface AddressAuthorizationService {

    void checkAccess(UUID currentUserId, boolean isAdmin, UUID targetUserId);

}
