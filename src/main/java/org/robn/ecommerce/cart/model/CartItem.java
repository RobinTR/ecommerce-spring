package org.robn.ecommerce.cart.model;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.robn.ecommerce.cart.model.enums.CartItemStatus;
import org.robn.ecommerce.common.model.BaseDomainModel;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
@SuperBuilder
public class CartItem extends BaseDomainModel {

    private UUID id;
    private UUID cartId;
    private Long productId;
    private Integer quantity;
    private BigDecimal discountAmount;
    private CartItemStatus cartItemStatus;

}
