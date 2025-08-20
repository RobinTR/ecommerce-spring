package org.robn.ecommerce.cart.port.adapter;

import lombok.RequiredArgsConstructor;
import org.robn.ecommerce.cart.mapper.GuestCartEntityToDomainMapper;
import org.robn.ecommerce.cart.model.GuestCart;
import org.robn.ecommerce.cart.model.entity.GuestCartEntity;
import org.robn.ecommerce.cart.model.enums.CartStatus;
import org.robn.ecommerce.cart.port.GuestCartReadPort;
import org.robn.ecommerce.cart.repository.GuestCartRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class GuestCartAdapter implements GuestCartReadPort {

    private final GuestCartRepository guestCartRepository;
    private final GuestCartEntityToDomainMapper guestCartEntityToDomainMapper;

    @Override
    public List<GuestCart> findAllBySessionId(final UUID sessionId) {
        final List<GuestCartEntity> guestCartEntities = guestCartRepository.findAllBySessionId(sessionId);

        return guestCartEntityToDomainMapper.map(guestCartEntities);
    }

    @Override
    public Optional<GuestCart> findBySessionIdAndCartStatus(final UUID sessionId, final CartStatus cartStatus) {
        final Optional<GuestCartEntity> guestCartEntity = guestCartRepository.findBySessionIdAndCartStatus(sessionId, cartStatus);

        return guestCartEntity.map(guestCartEntityToDomainMapper::map);
    }

}
