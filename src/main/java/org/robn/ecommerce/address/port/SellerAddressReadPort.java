package org.robn.ecommerce.address.port;

import org.robn.ecommerce.address.model.SellerAddress;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface SellerAddressReadPort {

    List<SellerAddress> findAllBySellerId(UUID sellerId);

    Optional<SellerAddress> findByAddressId(UUID addressId);

}
