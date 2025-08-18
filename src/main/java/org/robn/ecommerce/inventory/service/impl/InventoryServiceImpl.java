package org.robn.ecommerce.inventory.service.impl;

import lombok.RequiredArgsConstructor;
import org.robn.ecommerce.inventory.exception.InventoryAlreadyExistsException;
import org.robn.ecommerce.inventory.exception.InventoryByProductAndWarehouseNotFoundException;
import org.robn.ecommerce.inventory.exception.InventoryNotFoundException;
import org.robn.ecommerce.inventory.model.Inventory;
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
    public List<Inventory> findAllByWarehouseId(final Long warehouseId) {
        return readPort.findAllByWarehouseId(warehouseId);
    }

    @Override
    public Inventory findById(final Long id) {
        return getInventoryOrThrow(id);
    }

    @Override
    public Inventory findByProductIdAndWarehouseId(final Long productId, final Long warehouseId) {
        return readPort.findByProductIdAndWarehouseId(productId, warehouseId).orElseThrow(() -> InventoryByProductAndWarehouseNotFoundException.of(productId, warehouseId));
    }

    @Override
    @Transactional
    public Inventory create(final InventoryCreateRequest inventoryCreateRequest) {

        final Long productId = inventoryCreateRequest.productId();
        final Long warehouseId = inventoryCreateRequest.warehouseId();

        if (existsByProductIdAndWarehouseId(productId, warehouseId)) {
            throw InventoryAlreadyExistsException.of(
                    productId,
                    warehouseId
            );
        }

        final Inventory inventory = createRequestToDomainMapper.map(inventoryCreateRequest);

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
        return readPort.findById(id).orElseThrow(() -> new InventoryNotFoundException(id));
    }

    private boolean existsByProductIdAndWarehouseId(final Long productId, final Long warehouseId) {
        return readPort.findByProductIdAndWarehouseId(productId, warehouseId).isPresent();
    }

}
