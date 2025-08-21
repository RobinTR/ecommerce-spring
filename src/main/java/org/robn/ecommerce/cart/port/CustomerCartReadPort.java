package org.robn.ecommerce.cart.port;

import org.robn.ecommerce.cart.model.CustomerCart;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CustomerCartReadPort {

    List<CustomerCart> findAllByCustomerId(UUID customerId);

    Optional<CustomerCart> findByCartId(UUID cartId);

}
