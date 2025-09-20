package org.robn.ecommerce.cart.port.adapter;

import lombok.RequiredArgsConstructor;
import org.robn.ecommerce.cart.model.GuestCart;
import org.robn.ecommerce.cart.model.entity.GuestCartEntity;
import org.robn.ecommerce.cart.model.enums.CartStatus;
import org.robn.ecommerce.cart.model.mapper.GuestCartDomainToEntityMapper;
import org.robn.ecommerce.cart.model.mapper.GuestCartEntityToDomainMapper;
import org.robn.ecommerce.cart.port.GuestCartReadPort;
import org.robn.ecommerce.cart.port.GuestCartSavePort;
import org.robn.ecommerce.cart.repository.GuestCartRepository;
import org.springframework.stereotype.Component;

import java.util.List;
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
    public List<GuestCart> findAllByGuestIdAndCartStatus(final UUID guestId, final CartStatus cartStatus) {
        final List<GuestCartEntity> guestCartEntity = guestCartRepository.findAllByGuestIdAndCartStatus(guestId, cartStatus);

        return guestCartEntityToDomainMapper.map(guestCartEntity);
    }

    @Override
    public GuestCart save(final GuestCart guestCart) {
        final GuestCartEntity guestCartEntity = guestCartDomainToEntityMapper.map(guestCart);
        final GuestCartEntity savedGuestCartEntity = guestCartRepository.save(guestCartEntity);

        return guestCartEntityToDomainMapper.map(savedGuestCartEntity);
    }

}
