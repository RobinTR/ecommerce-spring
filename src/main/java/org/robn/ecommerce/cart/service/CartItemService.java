package org.robn.ecommerce.cart.service;

import org.robn.ecommerce.cart.model.CartItem;
import org.robn.ecommerce.cart.model.enums.CartItemStatus;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CartItemService {

    List<CartItem> findAllByCartId(UUID cartId);

    List<CartItem> findAllByCartIdAndStatus(UUID cartId, CartItemStatus status);

    Optional<CartItem> findByCartIdAndProductId(UUID cartId, Long productId);

    int getTotalItemCount(UUID cartId);

    CartItem create(UUID cartId, Long productId, int quantity);

    CartItem updateQuantity(UUID cartId, Long productId, int quantity);

}
