package org.robn.ecommerce.seller.port;

import org.robn.ecommerce.seller.model.Seller;

import java.util.Optional;

public interface SellerReadPort {

    Optional<Seller> findByEmail(String email);

}
