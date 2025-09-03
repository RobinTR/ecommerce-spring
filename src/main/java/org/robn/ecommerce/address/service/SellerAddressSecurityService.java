package org.robn.ecommerce.address.service;

import java.util.UUID;

public interface SellerAddressSecurityService {

    boolean isOwner(UUID addressId, UUID targetSellerId);

}
