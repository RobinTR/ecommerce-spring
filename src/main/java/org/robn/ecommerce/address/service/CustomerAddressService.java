package org.robn.ecommerce.address.service;

import org.robn.ecommerce.address.model.CustomerAddress;
import org.robn.ecommerce.address.model.request.CustomerAddressCreateRequest;
import org.robn.ecommerce.address.model.request.CustomerAddressUpdateRequest;

import java.util.List;
import java.util.UUID;

public interface CustomerAddressService {

    List<CustomerAddress> findAllByCustomerId(UUID customerId, UUID currentUserId, boolean isAdmin);

    CustomerAddress findByAddressId(UUID addressId, UUID customerId, boolean isAdmin);

    void create(CustomerAddressCreateRequest customerAddressCreateRequest, UUID userId, boolean isAdmin);

    void update(CustomerAddressUpdateRequest customerAddressUpdateRequest, UUID addressId, UUID userId, boolean isAdmin);

}
