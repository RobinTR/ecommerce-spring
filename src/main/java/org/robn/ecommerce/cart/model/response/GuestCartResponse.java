package org.robn.ecommerce.cart.model.response;

import org.robn.ecommerce.cart.model.enums.CartStatus;

import java.math.BigDecimal;

public record GuestCartResponse(

        String id,
        String guestId,
        BigDecimal totalPrice,
        CartStatus cartStatus

) {
}
