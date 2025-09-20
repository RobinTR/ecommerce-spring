package org.robn.ecommerce.cart.service.impl;

import lombok.RequiredArgsConstructor;
import org.robn.ecommerce.auth.port.SecurityReadPort;
import org.robn.ecommerce.cart.exception.CustomerCartNotFoundException;
import org.robn.ecommerce.cart.model.Cart;
import org.robn.ecommerce.cart.model.CustomerCart;
import org.robn.ecommerce.cart.model.enums.CartStatus;
import org.robn.ecommerce.cart.model.request.AddToCartRequest;
import org.robn.ecommerce.cart.port.CustomerCartReadPort;
import org.robn.ecommerce.cart.port.CustomerCartSavePort;
import org.robn.ecommerce.cart.service.CartItemService;
import org.robn.ecommerce.cart.service.CartService;
import org.robn.ecommerce.cart.service.CustomerCartService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CustomerCartServiceImpl implements CustomerCartService {

    private final CustomerCartReadPort customerCartReadPort;
    private final CustomerCartSavePort customerCartSavePort;
    private final SecurityReadPort securityReadPort;
    private final CartService cartService;
    private final CartItemService cartItemService;

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
    public CustomerCart create(final AddToCartRequest addToCartRequest) {
        final List<CustomerCart> existingCarts = customerCartReadPort.findAllByCustomerIdAndCartStatus(securityReadPort.getCurrentUserId(), CartStatus.ACTIVE);

        if (!existingCarts.isEmpty()) {
            return updateExistingCart(existingCarts.getFirst(), addToCartRequest);
        }

        return createNewCart(addToCartRequest);
    }

    private CustomerCart createNewCart(final AddToCartRequest request) {
        final CustomerCart customerCart = CustomerCart.builder()
                .customerId(securityReadPort.getCurrentUserId())
                .discountAmount(BigDecimal.ZERO)
                .cartStatus(CartStatus.ACTIVE)
                .build();
        final CustomerCart savedCart = customerCartSavePort.save(customerCart);
        cartItemService.create(customerCart.getId(), request.productId(), request.quantity());
        savedCart.setTotalPrice(cartService.calculateCartTotalPrice(savedCart.getId()));

        return savedCart;
    }

    private CustomerCart updateExistingCart(final CustomerCart existingCart, final AddToCartRequest request) {
        final Cart cart = cartService.update(existingCart.getId(), request);
        existingCart.setTotalPrice(cart.getTotalPrice());

        return existingCart;
    }

}
