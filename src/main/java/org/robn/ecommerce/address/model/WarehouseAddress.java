package org.robn.ecommerce.address.model;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.UUID;

@Getter
@Setter
@SuperBuilder
public class WarehouseAddress extends Address {

    private UUID warehouseId;

}
