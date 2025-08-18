package org.robn.ecommerce.inventory.port;

import org.robn.ecommerce.inventory.model.Inventory;

import java.util.List;
import java.util.Optional;

public interface InventoryReadPort {

    List<Inventory> findAllByProductId(Long productId);

    List<Inventory> findAllByWarehouseId(Long warehouseId);

    Optional<Inventory> findById(Long id);

    Optional<Inventory> findByProductIdAndWarehouseId(Long productId, Long warehouseId);

}
