package org.robn.ecommerce.address.service;

import org.robn.ecommerce.address.model.Address;
import org.robn.ecommerce.address.model.request.AddressCreateRequest;
import org.robn.ecommerce.address.model.request.AddressUpdateRequest;

import java.util.UUID;

public interface AddressService {

    Address create(AddressCreateRequest addressCreateRequest);

    Address update(UUID addressId, AddressUpdateRequest addressUpdateRequest);

}
