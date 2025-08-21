package org.robn.ecommerce.cart.service.impl;

import lombok.RequiredArgsConstructor;
import org.robn.ecommerce.cart.exception.CartNotFoundException;
import org.robn.ecommerce.cart.model.Cart;
import org.robn.ecommerce.cart.model.enums.CartStatus;
import org.robn.ecommerce.cart.port.CartReadPort;
import org.robn.ecommerce.cart.port.CartSavePort;
import org.robn.ecommerce.cart.service.CartService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CartServiceImpl implements CartService {

    private final CartReadPort cartReadPort;
    private final CartSavePort cartSavePort;

    @Override
    public Cart findById(UUID id) {
        return cartReadPort.findById(id).orElseThrow(() -> CartNotFoundException.of(id));
    }

    @Override
    @Transactional
    public Cart create() {
        Cart cart = Cart.builder()
                .id(UUID.randomUUID())
                .cartStatus(CartStatus.ACTIVE)
                .build();

        return cartSavePort.save(cart);
    }

}
