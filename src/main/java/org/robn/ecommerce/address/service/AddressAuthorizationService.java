package org.robn.ecommerce.address.service;

import java.util.UUID;

public interface AddressAuthorizationService {

    void checkAccessForCurrentUser(UUID targetUserId);

}
