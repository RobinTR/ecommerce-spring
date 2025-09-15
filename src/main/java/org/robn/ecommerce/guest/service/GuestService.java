package org.robn.ecommerce.guest.service;

import org.robn.ecommerce.guest.model.Guest;
import org.robn.ecommerce.guest.model.request.GuestCreateRequest;

import java.util.Optional;

public interface GuestService {

    Optional<Guest> findByDeviceId(String deviceId);

    Guest create(GuestCreateRequest guestCreateRequest);

}
