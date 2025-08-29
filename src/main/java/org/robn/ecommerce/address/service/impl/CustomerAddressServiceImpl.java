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
import org.robn.ecommerce.address.service.AddressAuthorizationService;
import org.robn.ecommerce.address.service.CustomerAddressService;
import org.robn.ecommerce.auth.util.EcoSecurityUtil;
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
    private final AddressAuthorizationService authorizationService;

    @Override
    public List<CustomerAddress> findAllByCustomerId(final UUID customerId) {
        return customerAddressReadPort.findAllByCustomerId(customerId);
    }

    @Override
    public CustomerAddress findByAddressId(final UUID addressId) {
        final CustomerAddress customerAddress = getCustomerAddress(addressId);

        if (EcoSecurityUtil.isCustomer()) {
            authorizationService.checkAccessForCurrentUser(customerAddress.getCustomerId());
        }

        return customerAddress;
    }

    @Override
    @Transactional
    public void create(final CustomerAddressCreateRequest customerAddressCreateRequest) {
        final CustomerAddress customerAddress = customerAddressCreateRequestToDomainMapper.map(customerAddressCreateRequest);
        customerAddress.setCustomerId(EcoSecurityUtil.getCurrentUserId());
        customerAddressSavePort.save(customerAddress);
    }

    @Override
    @Transactional
    public void update(final UUID addressId, final CustomerAddressUpdateRequest customerAddressUpdateRequest) {
        final CustomerAddress customerAddress = getCustomerAddress(addressId);

        if (EcoSecurityUtil.isCustomer()) {
            authorizationService.checkAccessForCurrentUser(customerAddress.getCustomerId());
        }

        customerAddressUpdateMapper.update(customerAddress, customerAddressUpdateRequest);
        customerAddressSavePort.save(customerAddress);
    }

    private CustomerAddress getCustomerAddress(final UUID addressId) {
        return customerAddressReadPort.findByAddressId(addressId).orElseThrow(() -> CustomerAddressNotFoundException.of(addressId));
    }

}
