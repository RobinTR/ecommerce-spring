package org.robn.ecommerce.cart.port;

import org.robn.ecommerce.cart.model.GuestCart;
import org.robn.ecommerce.cart.model.enums.CartStatus;

import java.util.List;
import java.util.UUID;

public interface GuestCartReadPort {

    List<GuestCart> findAllByGuestId(UUID guestId);

    List<GuestCart> findAllByGuestIdAndCartStatus(UUID guestId, CartStatus cartStatus);

}
