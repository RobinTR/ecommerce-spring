package org.robn.ecommerce.address.service;

import java.util.UUID;

public interface SellerAddressSecurityService {

    void checkOwnershipByAddressId(UUID addressId);

    void checkOwnershipBySellerId(UUID sellerId);

}
