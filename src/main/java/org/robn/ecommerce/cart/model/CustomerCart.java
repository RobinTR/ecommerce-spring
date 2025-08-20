package org.robn.ecommerce.cart.model;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.robn.ecommerce.cart.model.enums.CartStatus;
import org.robn.ecommerce.common.model.BaseDomainModel;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
@SuperBuilder
public final class CustomerCart extends BaseDomainModel {

    private UUID id;
    private UUID customerId;
    private BigDecimal subtotal;
    private BigDecimal taxAmount;
    private BigDecimal shippingAmount;
    private BigDecimal discountAmount;
    private BigDecimal grandTotal;
    private String currency;
    private CartStatus cartStatus;

}
