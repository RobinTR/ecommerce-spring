package org.robn.ecommerce.inventory.port;

import org.robn.ecommerce.inventory.model.Inventory;
import org.robn.ecommerce.inventory.model.enums.StockType;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface InventoryReadPort {

    List<Inventory> findAllByProductId(Long productId);

    List<Inventory> findAllByWarehouseId(UUID warehouseId);

    Optional<Inventory> findById(Long id);

    Optional<Inventory> findByIdAndStockType(Long id, StockType stockType);

    Optional<Inventory> findByProductIdAndWarehouseIdAndStockType(Long productId, UUID warehouseId, StockType stockType);

}

