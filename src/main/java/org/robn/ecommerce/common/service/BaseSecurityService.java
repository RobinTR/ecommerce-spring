package org.robn.ecommerce.common.service;

import java.util.UUID;

public interface BaseSecurityService {

    boolean isAdmin();

    boolean isCustomer();

    boolean isSeller();

    boolean isCurrentUser(UUID userId);

}
