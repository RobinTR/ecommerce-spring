package org.robn.ecommerce.cart.port;

import org.robn.ecommerce.cart.model.CartItem;
import org.robn.ecommerce.cart.model.enums.CartItemStatus;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CartItemReadPort {

    List<CartItem> findAllByCartId(UUID cartId);

    List<CartItem> findAllByCartIdAndCartItemStatus(UUID cartId, CartItemStatus cartItemStatus);

    Optional<CartItem> findByCartIdAndProductId(UUID cartId, Long productId);

    int getTotalItemCount(UUID cartId);

}
