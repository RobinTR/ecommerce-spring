package org.robn.ecommerce.cart.port.adapter;

import lombok.RequiredArgsConstructor;
import org.robn.ecommerce.cart.model.mapper.CartItemDomainToEntityMapper;
import org.robn.ecommerce.cart.model.mapper.CartItemEntityToDomainMapper;
import org.robn.ecommerce.cart.model.CartItem;
import org.robn.ecommerce.cart.model.entity.CartItemEntity;
import org.robn.ecommerce.cart.model.enums.CartItemStatus;
import org.robn.ecommerce.cart.port.CartItemReadPort;
import org.robn.ecommerce.cart.port.CartItemSavePort;
import org.robn.ecommerce.cart.repository.CartItemRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class CartItemAdapter implements CartItemReadPort, CartItemSavePort {

    private final CartItemRepository cartItemRepository;
    private final CartItemEntityToDomainMapper entityToDomainMapper;
    private final CartItemDomainToEntityMapper domainToEntityMapper;

    @Override
    public List<CartItem> findAllByCartId(final UUID cartId) {
        final List<CartItemEntity> cartItemEntities = cartItemRepository.findAllByCartId(cartId);

        return entityToDomainMapper.map(cartItemEntities);
    }

    @Override
    public List<CartItem> findAllByCartIdAndCartItemStatus(final UUID cartId, final CartItemStatus cartItemStatus) {
        final List<CartItemEntity> findAllByCardIdAndCartItemStatus = cartItemRepository.findAllByCartIdAndCartItemStatus(cartId, cartItemStatus);

        return entityToDomainMapper.map(findAllByCardIdAndCartItemStatus);
    }

    @Override
    public Optional<CartItem> findByCartIdAndProductId(final UUID cartId, final Long productId) {
        final Optional<CartItemEntity> cartItemEntity = cartItemRepository.findByCartIdAndProductId(cartId, productId);

        return cartItemEntity.map(entityToDomainMapper::map);
    }

    @Override
    public int getTotalItemCount(final UUID cartId) {
        return cartItemRepository.countByCartId(cartId);
    }

    @Override
    public CartItem save(final CartItem cartItem) {
        final CartItemEntity cartItemEntity = domainToEntityMapper.map(cartItem);
        final CartItemEntity savedCartItemEntity = cartItemRepository.save(cartItemEntity);

        return entityToDomainMapper.map(savedCartItemEntity);
    }

}
