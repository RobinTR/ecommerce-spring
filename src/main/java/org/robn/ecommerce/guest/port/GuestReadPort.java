package org.robn.ecommerce.guest.port;

import org.robn.ecommerce.guest.model.Guest;

import java.util.Optional;

public interface GuestReadPort {

    Optional<Guest> findByDeviceId(String deviceId);

}
