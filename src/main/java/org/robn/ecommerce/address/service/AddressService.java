package org.robn.ecommerce.address.service;

import org.robn.ecommerce.address.model.request.AddressCreateRequest;
import org.robn.ecommerce.address.model.request.AddressUpdateRequest;

import java.util.UUID;

public interface AddressService {

    void create(AddressCreateRequest addressCreateRequest);

    void update(UUID addressId, AddressUpdateRequest addressUpdateRequest);

}
