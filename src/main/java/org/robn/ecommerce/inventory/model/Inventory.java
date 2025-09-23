package org.robn.ecommerce.inventory.model;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.robn.ecommerce.common.model.BaseDomainModel;
import org.robn.ecommerce.inventory.model.enums.StockType;

import java.util.UUID;

@Getter
@Setter
@SuperBuilder
public class Inventory extends BaseDomainModel {

    private Long id;
    private Long productId;
    private UUID warehouseId;
    private StockType stockType;
    private Integer quantity;

}
