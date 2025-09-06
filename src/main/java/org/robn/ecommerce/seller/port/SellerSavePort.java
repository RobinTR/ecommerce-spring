package org.robn.ecommerce.seller.port;

import org.robn.ecommerce.seller.model.Seller;

public interface SellerSavePort {

    Seller save(Seller seller);

}
