package org.robn.ecommerce.cart.port;

import org.robn.ecommerce.cart.model.CustomerCart;
import org.robn.ecommerce.cart.model.enums.CartStatus;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CustomerCartReadPort {

    List<CustomerCart> findAllByCustomerId(UUID customerId);

    List<CustomerCart> findAllByCustomerIdAndCartStatus(UUID customerId, CartStatus cartStatus);

    Optional<CustomerCart> findByCartId(UUID cartId);

    boolean existsByIdAndCustomerId(UUID cartId, UUID customerId);

}
