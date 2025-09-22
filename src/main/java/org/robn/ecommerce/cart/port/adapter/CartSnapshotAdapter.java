package org.robn.ecommerce.cart.port.adapter;

import lombok.RequiredArgsConstructor;
import org.robn.ecommerce.cart.model.CartSnapshot;
import org.robn.ecommerce.cart.model.entity.CartSnapshotEntity;
import org.robn.ecommerce.cart.model.mapper.CartSnapshotDomainToEntityMapper;
import org.robn.ecommerce.cart.model.mapper.CartSnapshotEntityToDomainMapper;
import org.robn.ecommerce.cart.port.CartSnapshotReadPort;
import org.robn.ecommerce.cart.port.CartSnapshotSavePort;
import org.robn.ecommerce.cart.repository.CartSnapshotRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class CartSnapshotAdapter implements CartSnapshotReadPort, CartSnapshotSavePort {

    private final CartSnapshotRepository cartSnapshotRepository;
    private final CartSnapshotEntityToDomainMapper entityToDomainMapper;
    private final CartSnapshotDomainToEntityMapper domainToEntityMapper;

    @Override
    public Optional<CartSnapshot> findById(final UUID id) {
        final Optional<CartSnapshotEntity> cartSnapshotEntity = cartSnapshotRepository.findById(id);

        return cartSnapshotEntity.map(entityToDomainMapper::map);
    }

    @Override
    public List<CartSnapshot> findAllByCartId(final UUID cartId) {
        final List<CartSnapshotEntity> entities = cartSnapshotRepository.findAllByCartId(cartId);

        return entityToDomainMapper.map(entities);
    }

    @Override
    public CartSnapshot save(final CartSnapshot cartSnapshot) {
        final CartSnapshotEntity cartSnapshotEntity = domainToEntityMapper.map(cartSnapshot);
        final CartSnapshotEntity savedEntity = cartSnapshotRepository.save(cartSnapshotEntity);

        return entityToDomainMapper.map(savedEntity);
    }

}
