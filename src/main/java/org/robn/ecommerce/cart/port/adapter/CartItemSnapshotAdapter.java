package org.robn.ecommerce.cart.port.adapter;

import lombok.RequiredArgsConstructor;
import org.robn.ecommerce.cart.model.CartItemSnapshot;
import org.robn.ecommerce.cart.model.entity.CartItemSnapshotEntity;
import org.robn.ecommerce.cart.model.mapper.CartItemSnapshotDomainToEntityMapper;
import org.robn.ecommerce.cart.model.mapper.CartItemSnapshotEntityToDomainMapper;
import org.robn.ecommerce.cart.port.CartItemSnapshotReadPort;
import org.robn.ecommerce.cart.port.CartItemSnapshotSavePort;
import org.robn.ecommerce.cart.repository.CartItemSnapshotRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class CartItemSnapshotAdapter implements CartItemSnapshotReadPort, CartItemSnapshotSavePort {

    private final CartItemSnapshotRepository cartItemSnapshotRepository;
    private final CartItemSnapshotEntityToDomainMapper entityToDomainMapper;
    private final CartItemSnapshotDomainToEntityMapper domainToEntityMapper;

    @Override
    public List<CartItemSnapshot> findAllByCartSnapshotId(final UUID cartSnapshotId) {
        final List<CartItemSnapshotEntity> entities = cartItemSnapshotRepository.findAllByCartSnapshotId(cartSnapshotId);

        return entityToDomainMapper.map(entities);
    }

    @Override
    public CartItemSnapshot save(final CartItemSnapshot cartItemSnapshot) {
        final CartItemSnapshotEntity cartItemSnapshotEntity = domainToEntityMapper.map(cartItemSnapshot);
        final CartItemSnapshotEntity savedEntity = cartItemSnapshotRepository.save(cartItemSnapshotEntity);

        return entityToDomainMapper.map(savedEntity);
    }

}
