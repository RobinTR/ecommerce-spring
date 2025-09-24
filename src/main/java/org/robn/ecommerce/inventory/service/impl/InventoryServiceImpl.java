package org.robn.ecommerce.inventory.service.impl;

import lombok.RequiredArgsConstructor;
import org.robn.ecommerce.inventory.exception.InventoryAlreadyExistsException;
import org.robn.ecommerce.inventory.exception.InventoryByProductAndWarehouseAndStockTypeNotFoundException;
import org.robn.ecommerce.inventory.exception.InventoryNotFoundException;
import org.robn.ecommerce.inventory.model.Inventory;
import org.robn.ecommerce.inventory.model.enums.StockType;
import org.robn.ecommerce.inventory.model.mapper.InventoryCreateRequestToDomainMapper;
import org.robn.ecommerce.inventory.model.mapper.InventoryUpdateMapper;
import org.robn.ecommerce.inventory.model.request.InventoryCreateRequest;
import org.robn.ecommerce.inventory.model.request.InventoryUpdateRequest;
import org.robn.ecommerce.inventory.port.InventoryReadPort;
import org.robn.ecommerce.inventory.port.InventorySavePort;
import org.robn.ecommerce.inventory.service.InventoryService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class InventoryServiceImpl implements InventoryService {

    private final InventoryReadPort readPort;
    private final InventorySavePort savePort;
    private final InventoryCreateRequestToDomainMapper createRequestToDomainMapper;
    private final InventoryUpdateMapper updateMapper;

    @Override
    public List<Inventory> findAllByProductId(final Long productId) {
        return readPort.findAllByProductId(productId);
    }

    @Override
    public List<Inventory> findAllByWarehouseId(final UUID warehouseId) {
        return readPort.findAllByWarehouseId(warehouseId);
    }

    @Override
    public Inventory findById(final Long id) {
        return getInventoryOrThrow(id);
    }

    @Override
    public Inventory findByProductIdAndWarehouseIdAndStockType(final Long productId, final UUID warehouseId, final StockType stockType) {
        return readPort.findByProductIdAndWarehouseIdAndStockType(productId, warehouseId, stockType).orElseThrow(() -> InventoryByProductAndWarehouseAndStockTypeNotFoundException.of(productId, warehouseId, stockType));
    }

    @Override
    @Transactional
    public Inventory create(final InventoryCreateRequest inventoryCreateRequest) {
        final Long productId = inventoryCreateRequest.productId();
        final UUID warehouseId = inventoryCreateRequest.warehouseId();

        if (existsByProductIdAndWarehouseId(productId, warehouseId)) {
            throw InventoryAlreadyExistsException.of(
                    productId,
                    warehouseId
            );
        }

        final Inventory inventory = createRequestToDomainMapper.map(inventoryCreateRequest);
        inventory.setStockType(StockType.AVAILABLE);

        return savePort.save(inventory);
    }

    @Override
    @Transactional
    public Inventory update(final Long id, final InventoryUpdateRequest inventoryUpdateRequest) {
        final Inventory inventory = getInventoryOrThrow(id);
        updateMapper.update(inventory, inventoryUpdateRequest);

        return savePort.save(inventory);
    }

    private Inventory getInventoryOrThrow(final Long id) {
        return readPort.findById(id).orElseThrow(() -> InventoryNotFoundException.of(id));
    }

    private boolean existsByProductIdAndWarehouseId(final Long productId, final UUID warehouseId) {
        return readPort.findByProductIdAndWarehouseIdAndStockType(productId, warehouseId, StockType.AVAILABLE).isPresent();
    }

}
