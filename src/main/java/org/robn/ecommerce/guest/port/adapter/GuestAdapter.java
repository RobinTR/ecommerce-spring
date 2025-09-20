package org.robn.ecommerce.guest.port.adapter;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.robn.ecommerce.guest.model.Guest;
import org.robn.ecommerce.guest.model.entity.GuestEntity;
import org.robn.ecommerce.guest.model.mapper.GuestEntityToDomainMapper;
import org.robn.ecommerce.guest.model.mapper.GuestToEntityMapper;
import org.robn.ecommerce.guest.model.repository.GuestRepository;
import org.robn.ecommerce.guest.port.GuestReadPort;
import org.robn.ecommerce.guest.port.GuestSavePort;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class GuestAdapter implements GuestReadPort, GuestSavePort {

    private final GuestRepository guestRepository;
    private final GuestToEntityMapper guestToEntityMapper;
    private final GuestEntityToDomainMapper guestEntityToDomainMapper;
    private final EntityManager entityManager;

    @Override
    public Guest save(final Guest guest) {
        final GuestEntity guestEntity = guestToEntityMapper.map(guest);
        final GuestEntity savedEntity = guestRepository.save(guestEntity);
        entityManager.flush();

        return guestEntityToDomainMapper.map(savedEntity);
    }

    @Override
    public Optional<Guest> findByDeviceId(final String deviceId) {
        final Optional<GuestEntity> guestEntity = guestRepository.findByDeviceId(deviceId);

        return guestEntity.map(guestEntityToDomainMapper::map);
    }

}
