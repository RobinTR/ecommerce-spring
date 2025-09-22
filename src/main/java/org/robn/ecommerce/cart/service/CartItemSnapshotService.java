package org.robn.ecommerce.cart.service;

import org.robn.ecommerce.cart.model.CartItemSnapshot;

import java.util.List;
import java.util.UUID;

public interface CartItemSnapshotService {

    List<CartItemSnapshot> createCartItemSnapshotsFromCartSnapshotId(UUID cartSnapshotId);

}
