package org.robn.ecommerce.cart.service.impl;

import lombok.RequiredArgsConstructor;
import org.robn.ecommerce.cart.exception.CartItemByIdAndProductNotFoundException;
import org.robn.ecommerce.cart.model.CartItem;
import org.robn.ecommerce.cart.model.enums.CartItemStatus;
import org.robn.ecommerce.cart.model.request.AddToCartRequest;
import org.robn.ecommerce.cart.port.CartItemReadPort;
import org.robn.ecommerce.cart.port.CartItemSavePort;
import org.robn.ecommerce.cart.service.CartItemService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CartItemServiceImpl implements CartItemService {

    private final CartItemReadPort cartItemReadPort;
    private final CartItemSavePort cartItemSavePort;

    @Override
    public List<CartItem> findAllByCartId(final UUID cartId) {
        return cartItemReadPort.findAllByCartId(cartId);
    }

    @Override
    public List<CartItem> findAllByCartIdAndStatus(final UUID cartId, final CartItemStatus status) {
        return cartItemReadPort.findAllByCartIdAndCartItemStatus(cartId, status);
    }

    @Override
    public CartItem findByCartIdAndProductId(final UUID cartId, final Long productId) {
        return cartItemReadPort.findByCartIdAndProductId(cartId, productId).orElseThrow(() -> CartItemByIdAndProductNotFoundException.of(cartId, productId));
    }

    @Override
    public int getTotalItemCount(final UUID cartId) {
        return cartItemReadPort.getTotalItemCount(cartId);
    }

    @Override
    @Transactional
    public CartItem addToCart(final AddToCartRequest addToCartRequest) {
        return null;
    }

}
