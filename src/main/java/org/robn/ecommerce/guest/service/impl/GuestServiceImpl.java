package org.robn.ecommerce.guest.service.impl;

import lombok.RequiredArgsConstructor;
import org.robn.ecommerce.guest.model.Guest;
import org.robn.ecommerce.guest.model.mapper.GuestCreateRequestToDomainMapper;
import org.robn.ecommerce.guest.model.request.GuestCreateRequest;
import org.robn.ecommerce.guest.port.GuestReadPort;
import org.robn.ecommerce.guest.port.GuestSavePort;
import org.robn.ecommerce.guest.service.GuestService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class GuestServiceImpl implements GuestService {

    private final GuestReadPort guestReadPort;
    private final GuestSavePort guestSavePort;
    private final GuestCreateRequestToDomainMapper createRequestToDomainMapper;

    @Override
    public Optional<Guest> findByDeviceId(final String deviceId) {
        return guestReadPort.findByDeviceId(deviceId);
    }

    @Override
    @Transactional
    public Guest create(final GuestCreateRequest guestCreateRequest) {
        final Guest guest = createRequestToDomainMapper.map(guestCreateRequest);

        return guestSavePort.save(guest);
    }

}
