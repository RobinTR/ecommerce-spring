package org.robn.ecommerce.address.port;

import org.robn.ecommerce.address.model.SellerAddress;

public interface SellerAddressSavePort {

    SellerAddress save(SellerAddress sellerAddress);

}
