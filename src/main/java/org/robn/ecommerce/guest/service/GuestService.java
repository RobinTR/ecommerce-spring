package org.robn.ecommerce.guest.service;

import org.robn.ecommerce.guest.model.Guest;

import java.util.Optional;

public interface GuestService {

    Optional<Guest> findByDeviceId(String deviceId);

    Guest create(String deviceId);

}
