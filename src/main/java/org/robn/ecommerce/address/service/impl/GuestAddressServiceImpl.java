package org.robn.ecommerce.address.service.impl;

import lombok.RequiredArgsConstructor;
import org.robn.ecommerce.address.exception.GuestAddressNotFoundException;
import org.robn.ecommerce.address.model.GuestAddress;
import org.robn.ecommerce.address.model.mapper.GuestAddressCreateRequestToDomainMapper;
import org.robn.ecommerce.address.model.mapper.GuestAddressUpdateMapper;
import org.robn.ecommerce.address.model.mapper.GuestAddressUpdateRequestToDomainMapper;
import org.robn.ecommerce.address.model.request.GuestAddressCreateRequest;
import org.robn.ecommerce.address.model.request.GuestAddressUpdateRequest;
import org.robn.ecommerce.address.port.GuestAddressReadPort;
import org.robn.ecommerce.address.port.GuestAddressSavePort;
import org.robn.ecommerce.address.service.GuestAddressService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class GuestAddressServiceImpl implements GuestAddressService {

    private final GuestAddressReadPort guestAddressReadPort;
    private final GuestAddressSavePort guestAddressSavePort;
    private final GuestAddressCreateRequestToDomainMapper guestAddressCreateRequestToDomainMapper;
    private final GuestAddressUpdateRequestToDomainMapper guestAddressUpdateRequestToDomainMapper;
    private final GuestAddressUpdateMapper guestAddressUpdateMapper;

    @Override
    public List<GuestAddress> findAllBySessionId(final String sessionId) {
        return guestAddressReadPort.findAllBySessionId(sessionId);
    }

    @Override
    public GuestAddress findByAddressId(final UUID addressId) {
        return getGuestAddress(addressId);
    }

    @Override
    @Transactional
    public void create(final String sessionId, final GuestAddressCreateRequest guestAddressCreateRequest) {
        final GuestAddress guestAddress = guestAddressCreateRequestToDomainMapper.map(guestAddressCreateRequest);
        guestAddress.setSessionId(sessionId);
        guestAddressSavePort.save(guestAddress);
    }

    @Override
    @Transactional
    public void update(final UUID addressId, final GuestAddressUpdateRequest guestAddressUpdateRequest) {
        final GuestAddress guestAddress = getGuestAddress(addressId);
        guestAddressUpdateMapper.update(guestAddress, guestAddressUpdateRequest);
        guestAddressSavePort.save(guestAddress);
    }

    private GuestAddress getGuestAddress(final UUID addressId) {
        return guestAddressReadPort.findByAddressId(addressId).orElseThrow(() -> GuestAddressNotFoundException.of(addressId));
    }

}
