package org.robn.ecommerce.cart.service;

import org.robn.ecommerce.cart.model.GuestCart;
import org.robn.ecommerce.cart.model.enums.CartStatus;

import java.util.List;
import java.util.UUID;

public interface GuestCartService {

    List<GuestCart> findAllByGuestId(UUID guestId);

    GuestCart findByGuestIdAndCartStatus(UUID guestId, CartStatus cartStatus);

}
