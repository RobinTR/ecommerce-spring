package org.robn.ecommerce.address.service.impl;

import lombok.RequiredArgsConstructor;
import org.robn.ecommerce.address.exception.CustomerAddressNotFoundException;
import org.robn.ecommerce.address.model.CustomerAddress;
import org.robn.ecommerce.address.model.mapper.CustomerAddressCreateRequestToDomainMapper;
import org.robn.ecommerce.address.model.mapper.CustomerAddressUpdateMapper;
import org.robn.ecommerce.address.model.request.CustomerAddressCreateRequest;
import org.robn.ecommerce.address.model.request.CustomerAddressUpdateRequest;
import org.robn.ecommerce.address.port.CustomerAddressReadPort;
import org.robn.ecommerce.address.port.CustomerAddressSavePort;
import org.robn.ecommerce.address.service.CustomerAddressService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CustomerAddressServiceImpl implements CustomerAddressService {

    private final CustomerAddressReadPort customerAddressReadPort;
    private final CustomerAddressSavePort customerAddressSavePort;
    private final CustomerAddressCreateRequestToDomainMapper customerAddressCreateRequestToDomainMapper;
    private final CustomerAddressUpdateMapper customerAddressUpdateMapper;

    @Override
    @Transactional
    public void create(final CustomerAddressCreateRequest customerAddressCreateRequest) {
        final CustomerAddress customerAddress = customerAddressCreateRequestToDomainMapper.map(customerAddressCreateRequest);
        customerAddressSavePort.save(customerAddress);
    }

    @Override
    @Transactional
    public void update(final UUID customerId, final UUID addressId, final CustomerAddressUpdateRequest customerAddressUpdateRequest) {
        final CustomerAddress customerAddress = getCustomerAddress(customerId, addressId);
        customerAddressUpdateMapper.update(customerAddress, customerAddressUpdateRequest);
        customerAddressSavePort.save(customerAddress);
    }

    private CustomerAddress getCustomerAddress(final UUID customerId, final UUID addressId) {
        return customerAddressReadPort.findByCustomerIdAndAddressId(customerId, addressId).orElseThrow(() -> new CustomerAddressNotFoundException(addressId));
    }

}
