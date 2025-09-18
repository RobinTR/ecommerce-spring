package org.robn.ecommerce.cart.service.impl;

import lombok.RequiredArgsConstructor;
import org.robn.ecommerce.cart.exception.CartNotFoundException;
import org.robn.ecommerce.cart.model.Cart;
import org.robn.ecommerce.cart.model.CartItem;
import org.robn.ecommerce.cart.model.enums.CartStatus;
import org.robn.ecommerce.cart.model.request.AddToCartRequest;
import org.robn.ecommerce.cart.port.CartReadPort;
import org.robn.ecommerce.cart.port.CartSavePort;
import org.robn.ecommerce.cart.service.CartItemService;
import org.robn.ecommerce.cart.service.CartService;
import org.robn.ecommerce.product.service.ProductService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CartServiceImpl implements CartService {

    private final CartReadPort cartReadPort;
    private final CartSavePort cartSavePort;
    private final CartItemService cartItemService;
    private final ProductService productService;

    @Override
    public Cart findById(UUID id) {
        return cartReadPort.findById(id).orElseThrow(() -> CartNotFoundException.of(id));
    }

    @Override
    public BigDecimal calculateCartTotalPrice(final UUID cartId) {
        final List<CartItem> cartItems = cartItemService.findAllByCartId(cartId);
        BigDecimal totalPrice = BigDecimal.ZERO;

        for (CartItem cartItem : cartItems) {
            final BigDecimal productPrice = productService.findById(cartItem.getProductId()).getPrice();
            final BigDecimal calculatedTotalCartItemPrice = productPrice.multiply(BigDecimal.valueOf(cartItem.getQuantity()));
            totalPrice = totalPrice.add(calculatedTotalCartItemPrice.subtract(cartItem.getDiscountAmount()));
        }

        return totalPrice;
    }

    @Override
    @Transactional
    public Cart create(final AddToCartRequest addToCartRequest) {
        final Cart cart = Cart.builder()
                .id(UUID.randomUUID())
                .cartStatus(CartStatus.ACTIVE)
                .build();
        final Cart savedCart = cartSavePort.save(cart);
        cartItemService.create(savedCart.getId(), addToCartRequest.productId(), addToCartRequest.quantity());

        return savedCart;
    }

}
