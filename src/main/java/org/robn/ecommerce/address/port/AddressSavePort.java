package org.robn.ecommerce.address.port;

import org.robn.ecommerce.address.model.Address;

public interface AddressSavePort {

    Address save(Address address);

}
