package org.robn.ecommerce.address.port;

import org.robn.ecommerce.address.model.GuestAddress;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface GuestAddressReadPort {

    List<GuestAddress> findAllBySessionId(String sessionId);

    Optional<GuestAddress> findByAddressId(UUID addressId);

}
