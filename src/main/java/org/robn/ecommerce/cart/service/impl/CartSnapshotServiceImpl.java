package org.robn.ecommerce.cart.service.impl;

import lombok.RequiredArgsConstructor;
import org.robn.ecommerce.cart.exception.CartSnapshotNotFoundException;
import org.robn.ecommerce.cart.model.Cart;
import org.robn.ecommerce.cart.model.CartSnapshot;
import org.robn.ecommerce.cart.model.mapper.CartToCartSnapshotMapper;
import org.robn.ecommerce.cart.port.CartSnapshotReadPort;
import org.robn.ecommerce.cart.port.CartSnapshotSavePort;
import org.robn.ecommerce.cart.service.CartItemSnapshotService;
import org.robn.ecommerce.cart.service.CartSnapshotService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CartSnapshotServiceImpl implements CartSnapshotService {

    final CartSnapshotReadPort cartSnapshotReadPort;
    final CartSnapshotSavePort cartSnapshotSavePort;
    final CartToCartSnapshotMapper cartToCartSnapshotMapper;
    final CartItemSnapshotService cartItemSnapshotService;

    @Override
    public List<CartSnapshot> findAllByCartId(final UUID cartId) {
        return cartSnapshotReadPort.findAllByCartId(cartId);
    }

    @Override
    public CartSnapshot findById(final UUID id) {
        return cartSnapshotReadPort.findById(id).orElseThrow(() -> CartSnapshotNotFoundException.of(id));
    }

    @Override
    @Transactional
    public CartSnapshot create(final Cart cart) {
        final CartSnapshot cartSnapshot = cartToCartSnapshotMapper.map(cart);
        final CartSnapshot savedCartSnapshot = cartSnapshotSavePort.save(cartSnapshot);
        cartItemSnapshotService.createCartItemSnapshotsFromCartSnapshotId(savedCartSnapshot.getId(), savedCartSnapshot.getCartId());

        return savedCartSnapshot;
    }

}
