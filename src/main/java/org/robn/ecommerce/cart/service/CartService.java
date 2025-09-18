package org.robn.ecommerce.cart.service;

import org.robn.ecommerce.cart.model.Cart;
import org.robn.ecommerce.cart.model.request.AddToCartRequest;

import java.math.BigDecimal;
import java.util.UUID;

public interface CartService {

    Cart findById(UUID id);

    BigDecimal calculateCartTotalPrice(UUID cartId);

    Cart create(AddToCartRequest addToCartRequest);

}
