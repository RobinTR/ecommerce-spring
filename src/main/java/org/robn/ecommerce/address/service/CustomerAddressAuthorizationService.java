package org.robn.ecommerce.address.service;

import java.util.UUID;

public interface CustomerAddressAuthorizationService {

    void checkAccess(UUID currentUserId, boolean isAdmin, UUID targetCustomerId);

}
