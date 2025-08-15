package org.robn.ecommerce.address.port;

import org.robn.ecommerce.address.model.Address;

import java.util.Optional;
import java.util.UUID;

public interface AddressReadPort {

    Optional<Address> findById(UUID id);

}
