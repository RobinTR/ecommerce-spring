package org.robn.ecommerce.inventory.port;

import org.robn.ecommerce.inventory.model.Inventory;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface InventoryReadPort {

    List<Inventory> findAllByProductId(Long productId);

    List<Inventory> findAllByWarehouseId(UUID warehouseId);

    Optional<Inventory> findById(Long id);

    Optional<Inventory> findByProductIdAndWarehouseId(Long productId, UUID warehouseId);

}
