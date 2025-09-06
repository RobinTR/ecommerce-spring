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
import org.robn.ecommerce.address.service.CustomerAddressSecurityService;
import org.robn.ecommerce.address.service.CustomerAddressService;
import org.robn.ecommerce.auth.port.SecurityReadPort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CustomerAddressServiceImpl implements CustomerAddressService {

    private final CustomerAddressReadPort customerAddressReadPort;
    private final CustomerAddressSavePort customerAddressSavePort;
    private final CustomerAddressCreateRequestToDomainMapper customerAddressCreateRequestToDomainMapper;
    private final CustomerAddressUpdateMapper customerAddressUpdateMapper;
    private final CustomerAddressSecurityService securityService;
    private final SecurityReadPort securityReadPort;

    @Override
    public List<CustomerAddress> findAllByCustomerId(final UUID customerId) {
        securityService.checkAccessByCustomerId(customerId);

        return customerAddressReadPort.findAllByCustomerId(customerId);
    }

    @Override
    public CustomerAddress findByAddressId(final UUID addressId) {
        securityService.checkAccessByAddressId(addressId);

        return getCustomerAddress(addressId);
    }

    @Override
    @Transactional
    public void create(final CustomerAddressCreateRequest customerAddressCreateRequest) {
        final CustomerAddress customerAddress = customerAddressCreateRequestToDomainMapper.map(customerAddressCreateRequest);
        customerAddress.setIsDefault(Boolean.FALSE);
        customerAddress.setCustomerId(securityReadPort.getCurrentUserId());
        customerAddressSavePort.save(customerAddress);
    }

    @Override
    @Transactional
    public void update(final UUID addressId, final CustomerAddressUpdateRequest customerAddressUpdateRequest) {
        securityService.checkAccessByAddressId(addressId);
        final CustomerAddress customerAddress = getCustomerAddress(addressId);
        customerAddressUpdateMapper.update(customerAddress, customerAddressUpdateRequest);
        customerAddressSavePort.save(customerAddress);
    }

    private CustomerAddress getCustomerAddress(final UUID addressId) {
        return customerAddressReadPort.findByAddressId(addressId).orElseThrow(() -> CustomerAddressNotFoundException.of(addressId));
    }

}
