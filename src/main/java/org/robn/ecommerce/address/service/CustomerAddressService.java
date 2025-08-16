package org.robn.ecommerce.address.service;

import org.robn.ecommerce.address.model.request.CustomerAddressCreateRequest;
import org.robn.ecommerce.address.model.request.CustomerAddressUpdateRequest;

import java.util.UUID;

public interface CustomerAddressService {

    void create(CustomerAddressCreateRequest customerAddressCreateRequest);

    void update(UUID customerId, UUID addressId, CustomerAddressUpdateRequest customerAddressUpdateRequest);

}
