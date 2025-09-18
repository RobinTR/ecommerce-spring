package org.robn.ecommerce.cart.model.response;

import org.robn.ecommerce.cart.model.enums.CartStatus;

import java.math.BigDecimal;

public record CustomerCartResponse(

        String id,
        String customerId,
        BigDecimal totalPrice,
        CartStatus cartStatus

) {
}
