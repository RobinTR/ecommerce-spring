package org.robn.ecommerce.cart.port;

import org.robn.ecommerce.cart.model.CartSnapshot;

public interface CartSnapshotSavePort {

    CartSnapshot save(CartSnapshot cartSnapshot);

}
