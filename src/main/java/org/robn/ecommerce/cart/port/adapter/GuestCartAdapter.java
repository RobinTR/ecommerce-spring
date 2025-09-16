package org.robn.ecommerce.cart.port.adapter;

import lombok.RequiredArgsConstructor;
import org.robn.ecommerce.cart.mapper.GuestCartDomainToEntityMapper;
import org.robn.ecommerce.cart.mapper.GuestCartEntityToDomainMapper;
import org.robn.ecommerce.cart.model.GuestCart;
import org.robn.ecommerce.cart.model.entity.GuestCartEntity;
import org.robn.ecommerce.cart.model.enums.CartStatus;
import org.robn.ecommerce.cart.port.GuestCartReadPort;
import org.robn.ecommerce.cart.port.GuestCartSavePort;
import org.robn.ecommerce.cart.repository.GuestCartRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class GuestCartAdapter implements GuestCartReadPort, GuestCartSavePort {

    private final GuestCartRepository guestCartRepository;
    private final GuestCartEntityToDomainMapper guestCartEntityToDomainMapper;
    private final GuestCartDomainToEntityMapper guestCartDomainToEntityMapper;

    @Override
    public List<GuestCart> findAllByGuestId(final UUID guestId) {
        final List<GuestCartEntity> guestCartEntities = guestCartRepository.findAllByGuestId(guestId);

        return guestCartEntityToDomainMapper.map(guestCartEntities);
    }

    @Override
    public Optional<GuestCart> findByGuestIdAndCartStatus(final UUID guestId, final CartStatus cartStatus) {
        final Optional<GuestCartEntity> guestCartEntity = guestCartRepository.findByGuestIdAndCartStatus(guestId, cartStatus);

        return guestCartEntity.map(guestCartEntityToDomainMapper::map);
    }

    @Override
    public GuestCart save(final GuestCart guestCart) {
        final GuestCartEntity guestCartEntity = guestCartDomainToEntityMapper.map(guestCart);
        final GuestCartEntity savedGuestCartEntity = guestCartRepository.save(guestCartEntity);

        return guestCartEntityToDomainMapper.map(savedGuestCartEntity);
    }

}
