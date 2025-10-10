package org.robn.ecommerce.cart.service;

import java.util.UUID;

public interface CartSecurityService {

    void checkAccessByCartId(UUID cartId);

    void requireAdminAuthentication();

    void requireCustomerAuthentication();

}
