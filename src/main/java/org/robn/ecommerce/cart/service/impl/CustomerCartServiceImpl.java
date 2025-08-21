package org.robn.ecommerce.cart.service.impl;

import lombok.RequiredArgsConstructor;
import org.robn.ecommerce.cart.exception.CustomerCartNotFoundException;
import org.robn.ecommerce.cart.model.CustomerCart;
import org.robn.ecommerce.cart.model.enums.CartStatus;
import org.robn.ecommerce.cart.port.CustomerCartReadPort;
import org.robn.ecommerce.cart.port.CustomerCartSavePort;
import org.robn.ecommerce.cart.service.CustomerCartService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CustomerCartServiceImpl implements CustomerCartService {

    private final CustomerCartReadPort customerCartReadPort;
    private final CustomerCartSavePort customerCartSavePort;

    @Override
    public List<CustomerCart> findAllByCustomerId(final UUID customerId) {
        return customerCartReadPort.findAllByCustomerId(customerId);
    }

    @Override
    public CustomerCart findByCartId(final UUID cartId) {
        return customerCartReadPort.findByCartId(cartId).orElseThrow(() -> CustomerCartNotFoundException.of(cartId));
    }

    @Override
    @Transactional
    public CustomerCart create(final CustomerCart customerCart) {
        final CustomerCart cart = CustomerCart.builder()
                .customerId(customerCart.getCustomerId())
                .id(customerCart.getId())
                .cartStatus(CartStatus.ACTIVE)
                .build();

        return customerCartSavePort.save(cart);
    }

}
