package org.robn.ecommerce.cart.service.impl;

import lombok.RequiredArgsConstructor;
import org.robn.ecommerce.cart.exception.CartItemByIdAndProductNotFoundException;
import org.robn.ecommerce.cart.model.CartItem;
import org.robn.ecommerce.cart.model.enums.CartItemStatus;
import org.robn.ecommerce.cart.port.CartItemReadPort;
import org.robn.ecommerce.cart.port.CartItemSavePort;
import org.robn.ecommerce.cart.service.CartItemService;
import org.robn.ecommerce.product.model.Product;
import org.robn.ecommerce.product.service.ProductService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CartItemServiceImpl implements CartItemService {

    private final CartItemReadPort cartItemReadPort;
    private final CartItemSavePort cartItemSavePort;
    private final ProductService productService;

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
    public CartItem create(final UUID cartId, final Long productId, final int quantity) {
        final Product product = productService.findById(productId);
        final CartItem cartItem = CartItem.builder()
                .cartId(cartId)
                .productId(product.getId())
                .quantity(quantity)
                .discountAmount(BigDecimal.ZERO)
                .cartItemStatus(CartItemStatus.ACTIVE)
                .build();

        return cartItemSavePort.save(cartItem);
    }

    @Override
    @Transactional
    public CartItem updateQuantity(final UUID cartId, final Long productId, final int quantity) {
        final CartItem cartItem = this.findByCartIdAndProductId(cartId, productId);
        cartItem.setQuantity(cartItem.getQuantity() + quantity);

        return cartItemSavePort.save(cartItem);
    }


}
