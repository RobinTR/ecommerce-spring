package org.robn.ecommerce.cart.port;

import org.robn.ecommerce.cart.model.CartItem;

public interface CartItemSavePort {

    CartItem addToCart(CartItem cartItem);

}
