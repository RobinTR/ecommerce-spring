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
import org.robn.ecommerce.address.service.CustomerAddressAuthorizationService;
import org.robn.ecommerce.address.service.CustomerAddressService;
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
    private final CustomerAddressAuthorizationService authorizationService;

    @Override
    public List<CustomerAddress> findAllByCustomerId(final UUID customerId, final UUID currentUserId, final boolean isAdmin) {
        final List<CustomerAddress> allByCustomerId = customerAddressReadPort.findAllByCustomerId(customerId);
        authorizationService.checkAccess(currentUserId, isAdmin, allByCustomerId.stream().findFirst().map(CustomerAddress::getCustomerId).orElseThrow(() -> CustomerAddressNotFoundException.of(customerId)));

        return allByCustomerId;
    }

    @Override
    public CustomerAddress findByAddressId(final UUID addressId, final UUID customerId, final boolean isAdmin) {
        CustomerAddress customerAddress = getCustomerAddress(addressId);
        authorizationService.checkAccess(customerId, isAdmin, customerAddress.getCustomerId());

        return customerAddress;
    }

    @Override
    @Transactional
    public void create(final CustomerAddressCreateRequest customerAddressCreateRequest, final UUID customerId, final boolean isAdmin) {
        final CustomerAddress customerAddress = customerAddressCreateRequestToDomainMapper.map(customerAddressCreateRequest);
        authorizationService.checkAccess(customerId, isAdmin, customerAddress.getCustomerId());
        customerAddressSavePort.save(customerAddress);
    }

    @Override
    @Transactional
    public void update(final CustomerAddressUpdateRequest customerAddressUpdateRequest, final UUID addressId, final UUID customerId, final boolean isAdmin) {
        final CustomerAddress customerAddress = getCustomerAddress(addressId);
        authorizationService.checkAccess(customerId, isAdmin, customerAddress.getCustomerId());
        customerAddressUpdateMapper.update(customerAddress, customerAddressUpdateRequest);
        customerAddressSavePort.save(customerAddress);
    }

    private CustomerAddress getCustomerAddress(final UUID addressId) {
        return customerAddressReadPort.findByAddressId(addressId).orElseThrow(() -> CustomerAddressNotFoundException.of(addressId));
    }

}
