package org.robn.ecommerce.address.port.adapter;

import lombok.RequiredArgsConstructor;
import org.robn.ecommerce.address.model.GuestAddress;
import org.robn.ecommerce.address.model.entity.GuestAddressEntity;
import org.robn.ecommerce.address.model.mapper.GuestAddressDomainToEntityMapper;
import org.robn.ecommerce.address.model.mapper.GuestAddressEntityToDomainMapper;
import org.robn.ecommerce.address.port.GuestAddressReadPort;
import org.robn.ecommerce.address.port.GuestAddressSavePort;
import org.robn.ecommerce.address.repository.GuestAddressRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class GuestAddressAdapter implements GuestAddressReadPort, GuestAddressSavePort {

    private final GuestAddressRepository guestAddressRepository;
    private final GuestAddressEntityToDomainMapper guestAddressEntityToDomainMapper;
    private final GuestAddressDomainToEntityMapper guestAddressDomainToEntityMapper;

    @Override
    public List<GuestAddress> findAllBySessionId(String sessionId) {
        List<GuestAddressEntity> guestAddressEntities = guestAddressRepository.findAllBySessionId(sessionId);

        return guestAddressEntityToDomainMapper.map(guestAddressEntities);
    }

    @Override
    public Optional<GuestAddress> findByAddressId(UUID addressId) {
        Optional<GuestAddressEntity> guestAddressEntity = guestAddressRepository.findById(addressId);

        return guestAddressEntity.map(guestAddressEntityToDomainMapper::map);
    }

    @Override
    public GuestAddress save(GuestAddress guestAddress) {
        GuestAddressEntity guestAddressEntity = guestAddressDomainToEntityMapper.map(guestAddress);
        GuestAddressEntity savedGuestAddressEntity = guestAddressRepository.save(guestAddressEntity);

        return guestAddressEntityToDomainMapper.map(savedGuestAddressEntity);
    }

}
