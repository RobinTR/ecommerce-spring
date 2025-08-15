package org.robn.ecommerce.address.service.impl;

import lombok.RequiredArgsConstructor;
import org.robn.ecommerce.address.exception.AddressNotFoundException;
import org.robn.ecommerce.address.model.Address;
import org.robn.ecommerce.address.model.mapper.AddressCreateRequestToDomainMapper;
import org.robn.ecommerce.address.model.mapper.AddressUpdateMapper;
import org.robn.ecommerce.address.model.request.AddressCreateRequest;
import org.robn.ecommerce.address.model.request.AddressUpdateRequest;
import org.robn.ecommerce.address.port.AddressReadPort;
import org.robn.ecommerce.address.port.AddressSavePort;
import org.robn.ecommerce.address.service.AddressService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AddressServiceImpl implements AddressService {

    private final AddressReadPort addressReadPort;
    private final AddressSavePort addressSavePort;
    private final AddressCreateRequestToDomainMapper addressCreateRequestToDomainMapper;
    private final AddressUpdateMapper addressUpdateMapper;

    @Override
    @Transactional
    public void create(final AddressCreateRequest addressCreateRequest) {
        final Address address = addressCreateRequestToDomainMapper.map(addressCreateRequest);
        addressSavePort.save(address);
    }

    @Override
    @Transactional
    public void update(final UUID id, final AddressUpdateRequest addressUpdateRequest) {
        final Address address = getExistingAddress(id);
        addressUpdateMapper.update(address, addressUpdateRequest);
        addressSavePort.save(address);
    }

    private Address getExistingAddress(final UUID id) {
        return addressReadPort.findById(id)
                .orElseThrow(() -> new AddressNotFoundException(id));
    }

}
