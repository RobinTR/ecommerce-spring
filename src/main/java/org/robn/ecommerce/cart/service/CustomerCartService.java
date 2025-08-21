package org.robn.ecommerce.cart.service;

import org.robn.ecommerce.cart.model.CustomerCart;

import java.util.List;
import java.util.UUID;

public interface CustomerCartService {

    List<CustomerCart> findAllByCustomerId(UUID customerId);

    CustomerCart findByCartId(UUID cartId);

    CustomerCart create(CustomerCart customerCart);

}
