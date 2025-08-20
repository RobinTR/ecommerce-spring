package org.robn.ecommerce.cart.service;

import org.robn.ecommerce.cart.model.Cart;

import java.util.UUID;

public interface CartService {

    Cart findById(UUID id);

    Cart create();

}
