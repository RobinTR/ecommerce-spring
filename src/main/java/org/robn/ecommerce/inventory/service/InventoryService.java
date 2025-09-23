package org.robn.ecommerce.inventory.service;

import org.robn.ecommerce.inventory.model.Inventory;
import org.robn.ecommerce.inventory.model.request.InventoryCreateRequest;
import org.robn.ecommerce.inventory.model.request.InventoryUpdateRequest;

import java.util.List;
import java.util.UUID;

public interface InventoryService {

    List<Inventory> findAllByProductId(Long productId);

    List<Inventory> findAllByWarehouseId(UUID warehouseId);

    Inventory findById(Long id);

    Inventory findByProductIdAndWarehouseId(Long productId, UUID warehouseId);

    Inventory create(InventoryCreateRequest inventoryCreateRequest);

    Inventory update(Long id, InventoryUpdateRequest inventoryUpdateRequest);

}
