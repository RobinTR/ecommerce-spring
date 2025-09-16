package org.robn.ecommerce.order.model;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.robn.ecommerce.common.model.BaseDomainModel;
import org.robn.ecommerce.order.model.enums.OrderStatus;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
@SuperBuilder
public class Order extends BaseDomainModel {

    private UUID id;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String city;
    private String district;
    private String neighborhood;
    private String fullAddress;
    private BigDecimal subtotal;
    private BigDecimal taxAmount;
    private BigDecimal shippingAmount;
    private BigDecimal discountAmount;
    private BigDecimal grandTotal;
    private String currency;
    private String promoCode;
    private String note;
    private OrderStatus orderStatus;

}
