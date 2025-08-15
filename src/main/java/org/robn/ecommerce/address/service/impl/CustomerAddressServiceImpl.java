package org.robn.ecommerce.address.service.impl;

import lombok.RequiredArgsConstructor;
import org.robn.ecommerce.address.model.CustomerAddress;
import org.robn.ecommerce.address.model.mapper.CustomerAddressCreateRequestToDomainMapper;
import org.robn.ecommerce.address.model.request.CustomerAddressCreateRequest;
import org.robn.ecommerce.address.model.request.CustomerAddressUpdateRequest;
import org.robn.ecommerce.address.port.CustomerAddressSavePort;
import org.robn.ecommerce.address.service.CustomerAddressService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CustomerAddressServiceImpl implements CustomerAddressService {

    private final CustomerAddressSavePort customerAddressSavePort;
    private final CustomerAddressCreateRequestToDomainMapper customerAddressCreateRequestToDomainMapper;

    @Override
    @Transactional
    public void create(CustomerAddressCreateRequest customerAddressCreateRequest) {
        CustomerAddress customerAddress = customerAddressCreateRequestToDomainMapper.map(customerAddressCreateRequest);
        customerAddressSavePort.save(customerAddress);
    }

    @Override
    @Transactional
    public void update(UUID customerId, UUID addressId, CustomerAddressUpdateRequest customerAddressUpdateRequest) {

    }
}
