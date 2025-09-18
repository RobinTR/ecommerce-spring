package org.robn.ecommerce.cart.service;

import org.robn.ecommerce.cart.model.CustomerCart;
import org.robn.ecommerce.cart.model.request.AddToCartRequest;

import java.util.List;
import java.util.UUID;

public interface CustomerCartService {

    List<CustomerCart> findAllByCustomerId(UUID customerId);

    CustomerCart findByCartId(UUID cartId);

    CustomerCart create(AddToCartRequest addToCartRequest);

}
