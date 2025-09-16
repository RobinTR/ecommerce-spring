package org.robn.ecommerce.cart.port;

import org.robn.ecommerce.cart.model.GuestCart;
import org.robn.ecommerce.cart.model.enums.CartStatus;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface GuestCartReadPort {

    List<GuestCart> findAllByGuestId(UUID guestId);

    Optional<GuestCart> findByGuestIdAndCartStatus(UUID guestId, CartStatus cartStatus);

}
