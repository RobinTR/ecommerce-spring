package org.robn.ecommerce.cart.port;

import org.robn.ecommerce.cart.model.Cart;

public interface CartSavePort {

    Cart save(Cart cart);

}
