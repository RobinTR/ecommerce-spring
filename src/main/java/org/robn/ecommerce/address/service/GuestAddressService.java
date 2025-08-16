package org.robn.ecommerce.address.service;

import org.robn.ecommerce.address.model.GuestAddress;
import org.robn.ecommerce.address.model.request.GuestAddressCreateRequest;
import org.robn.ecommerce.address.model.request.GuestAddressUpdateRequest;

import java.util.List;
import java.util.UUID;

public interface GuestAddressService {

    List<GuestAddress> findAllBySessionId(String sessionId);

    GuestAddress findByAddressId(UUID addressId);

    void create(String sessionId, GuestAddressCreateRequest guestAddressCreateRequest);

    void update(UUID addressId, GuestAddressUpdateRequest guestAddressUpdateRequest);

}
