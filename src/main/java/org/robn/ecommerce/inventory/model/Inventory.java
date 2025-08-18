package org.robn.ecommerce.inventory.model;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.robn.ecommerce.common.model.BaseDomainModel;
import org.robn.ecommerce.product.model.entity.ProductEntity;

@Getter
@Setter
@SuperBuilder
public class Inventory extends BaseDomainModel {

    private Long id;
    private Long productId;
    private Long warehouseId;
    private Integer stockQuantity;

}
