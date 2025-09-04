package org.robn.ecommerce.address.service;

import java.util.UUID;

public interface SellerAddressSecurityService {

    void checkAccessByAddressId(UUID addressId);

    void checkAccessBySellerId(UUID sellerId);

}
