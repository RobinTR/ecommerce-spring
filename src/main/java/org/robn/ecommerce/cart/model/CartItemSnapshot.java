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
public class CartItemSnapshot extends BaseDomainModel {

    private UUID id;
    private UUID cartSnapshotId;
    private UUID cartItemId;
    private Long productId;
    private UUID productSellerId;
    private Long productBrandId;
    private String productName;
    private String productDescription;
    private BigDecimal productPrice;
    private Integer quantity;
    private BigDecimal discountAmount;
    private CartItemStatus cartItemStatus;

}
