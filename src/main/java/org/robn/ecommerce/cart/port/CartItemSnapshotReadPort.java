package org.robn.ecommerce.cart.port;

import org.robn.ecommerce.cart.model.CartItemSnapshot;

import java.util.List;
import java.util.UUID;

public interface CartItemSnapshotReadPort {

    List<CartItemSnapshot> findAllByCartSnapshotId(UUID cartSnapshotId);

}
