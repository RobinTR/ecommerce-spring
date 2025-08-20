package org.robn.ecommerce.cart.service;

import org.robn.ecommerce.cart.model.CartItem;
import org.robn.ecommerce.cart.model.enums.CartItemStatus;
import org.robn.ecommerce.cart.model.request.AddToCartRequest;

import java.util.List;
import java.util.UUID;

public interface CartItemService {

    List<CartItem> findAllByCartId(UUID cartId);

    List<CartItem> findAllByCartIdAndStatus(UUID cartId, CartItemStatus status);

    CartItem findByCartIdAndProductId(UUID cartId, Long productId);

    int getTotalItemCount(UUID cartId);

    CartItem addToCart(AddToCartRequest addToCartRequest);

}
