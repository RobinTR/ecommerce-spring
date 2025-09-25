package org.robn.ecommerce.address.service;

import java.util.UUID;

public interface CustomerAddressSecurityService {

    void checkAccessByAddressId(UUID addressId);

    void checkAccessByCustomerId(UUID customerId);

    void requireCustomerAuthentication();

}
