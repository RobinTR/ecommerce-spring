package org.robn.ecommerce.cart.port;

import org.robn.ecommerce.cart.model.CartItemSnapshot;

public interface CartItemSnapshotSavePort {

    CartItemSnapshot save(CartItemSnapshot cartItemSnapshot);

}
