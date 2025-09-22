package org.robn.ecommerce.cart.port;

import org.robn.ecommerce.cart.model.CartItemSnapshot;

import java.util.List;

public interface CartItemSnapshotSavePort {

    CartItemSnapshot save(CartItemSnapshot cartItemSnapshot);

    List<CartItemSnapshot> saveAll(List<CartItemSnapshot> cartItemSnapshots);

}
