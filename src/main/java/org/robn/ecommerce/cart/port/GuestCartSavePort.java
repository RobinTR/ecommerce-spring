package org.robn.ecommerce.cart.port;

import org.robn.ecommerce.cart.model.GuestCart;

public interface GuestCartSavePort {

    GuestCart save(GuestCart guestCart);

}
