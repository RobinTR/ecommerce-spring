package org.robn.ecommerce.cart.service;

import org.robn.ecommerce.cart.model.GuestCart;
import org.robn.ecommerce.cart.model.enums.CartStatus;
import org.robn.ecommerce.cart.model.request.AddToCartRequest;

import java.util.List;
import java.util.UUID;

public interface GuestCartService {

    List<GuestCart> findAllByGuestId(UUID guestId);

    List<GuestCart> findAllByGuestIdAndCartStatus(UUID guestId, CartStatus cartStatus);

    GuestCart create(String deviceId, AddToCartRequest addToCartRequest);

}
