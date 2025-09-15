package org.robn.ecommerce.guest.port;

import org.robn.ecommerce.guest.model.Guest;

public interface GuestSavePort {

    Guest save(Guest guest);

}
