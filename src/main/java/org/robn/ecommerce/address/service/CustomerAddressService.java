package org.robn.ecommerce.address.service;

import org.robn.ecommerce.address.model.CustomerAddress;
import org.robn.ecommerce.address.model.request.CustomerAddressCreateRequest;
import org.robn.ecommerce.address.model.request.CustomerAddressUpdateRequest;

import java.util.List;
import java.util.UUID;

public interface CustomerAddressService {

    List<CustomerAddress> findAllByCustomerId(UUID customerId);

    CustomerAddress findByAddressId(UUID addressId);

    CustomerAddress create(CustomerAddressCreateRequest customerAddressCreateRequest);

    CustomerAddress update(UUID addressId, CustomerAddressUpdateRequest customerAddressUpdateRequest);

}
