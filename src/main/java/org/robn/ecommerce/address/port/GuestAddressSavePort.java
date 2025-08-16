package org.robn.ecommerce.address.port;

import org.robn.ecommerce.address.model.GuestAddress;

public interface GuestAddressSavePort {

    GuestAddress save(GuestAddress guestAddress);

}
