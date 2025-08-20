package org.robn.ecommerce.cart.port;

import org.robn.ecommerce.cart.model.Cart;
import org.robn.ecommerce.cart.model.enums.CartStatus;

import java.util.Optional;
import java.util.UUID;

public interface CartReadPort {

    Optional<Cart> findById(UUID id);

    Optional<Cart> findByIdAndCartStatus(UUID id, CartStatus cartStatus);

}
