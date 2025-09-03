package org.robn.ecommerce.address.service;

import java.util.UUID;

public interface CustomerAddressSecurityService {

    void checkOwnershipByAddressId(UUID addressId);

    void checkOwnershipByCustomerId(UUID customerId);

}
