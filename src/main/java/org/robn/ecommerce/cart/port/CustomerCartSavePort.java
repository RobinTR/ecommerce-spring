package org.robn.ecommerce.cart.port;

import org.robn.ecommerce.cart.model.CustomerCart;

public interface CustomerCartSavePort {

    CustomerCart save(CustomerCart customerCart);

}
