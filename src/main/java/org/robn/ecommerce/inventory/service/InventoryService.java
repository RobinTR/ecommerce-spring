package org.robn.ecommerce.inventory.service;

import org.robn.ecommerce.inventory.model.Inventory;
import org.robn.ecommerce.inventory.model.request.InventoryCreateRequest;
import org.robn.ecommerce.inventory.model.request.InventoryUpdateRequest;

import java.util.List;

public interface InventoryService {

    List<Inventory> findAllByProductId(Long productId);

    List<Inventory> findAllByWarehouseId(Long warehouseId);

    Inventory findById(Long id);

    Inventory findByProductIdAndWarehouseId(Long productId, Long warehouseId);

    Inventory create(InventoryCreateRequest inventoryCreateRequest);

    Inventory update(final Long id, InventoryUpdateRequest inventoryUpdateRequest);

}
