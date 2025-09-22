package org.robn.ecommerce.cart.port;

import org.robn.ecommerce.cart.model.CartSnapshot;

import java.util.Optional;
import java.util.UUID;

public interface CartSnapshotReadPort {

    Optional<CartSnapshot> findById(UUID id);

}
