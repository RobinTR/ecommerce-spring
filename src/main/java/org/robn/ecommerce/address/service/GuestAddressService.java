package org.robn.ecommerce.address.service;

import org.robn.ecommerce.address.model.GuestAddress;
import org.robn.ecommerce.address.model.request.GuestAddressCreateRequest;
import org.robn.ecommerce.address.model.request.GuestAddressUpdateRequest;
import org.robn.ecommerce.guest.model.request.GuestCreateRequest;

import java.util.List;
import java.util.UUID;

public interface GuestAddressService {

    List<GuestAddress> findAllBySessionId(String sessionId);

    GuestAddress findByAddressId(UUID addressId);

    GuestAddress create(GuestCreateRequest guestCreateRequest, GuestAddressCreateRequest guestAddressCreateRequest);

    GuestAddress update(UUID addressId, GuestAddressUpdateRequest guestAddressUpdateRequest);

}
