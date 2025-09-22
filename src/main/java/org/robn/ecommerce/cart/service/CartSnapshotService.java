package org.robn.ecommerce.cart.service;

import org.robn.ecommerce.cart.model.Cart;
import org.robn.ecommerce.cart.model.CartSnapshot;

import java.util.List;
import java.util.UUID;

public interface CartSnapshotService {

    List<CartSnapshot> findAllByCartId(UUID cartId);

    CartSnapshot findById(UUID id);

    CartSnapshot create(Cart cart);

}
