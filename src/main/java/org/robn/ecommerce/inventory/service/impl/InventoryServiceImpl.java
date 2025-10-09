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
import org.robn.ecommerce.inventory.service.InventorySecurityService;
import org.robn.ecommerce.inventory.service.InventoryService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class InventoryServiceImpl implements InventoryService {

    private final InventoryReadPort inventoryReadPort;
    private final InventorySavePort inventorySavePort;
    private final InventoryCreateRequestToDomainMapper createRequestToDomainMapper;
    private final InventoryUpdateMapper updateMapper;
    private final InventorySecurityService securityService;

    @Override
    public List<Inventory> findAllByWarehouseId(final UUID warehouseId) {
        securityService.checkAccessByWarehouseId(warehouseId);

        return inventoryReadPort.findAllByWarehouseId(warehouseId);
    }

    @Override
    public List<Inventory> findAllByProductId(final Long productId) {
        securityService.checkAccessByProductId(productId);

        return inventoryReadPort.findAllByProductId(productId);
    }

    // No security check - used for both administrative queries (via controller security)
    // and customer checkout operations (public stock verification)
    @Override
    public Inventory findAvailableByProductIdAndWarehouseId(final Long productId, final UUID warehouseId) {
        return inventoryReadPort.findByProductIdAndWarehouseIdAndStockType(productId, warehouseId, StockType.AVAILABLE).orElseThrow(() -> InventoryByProductAndWarehouseAndStockTypeNotFoundException.of(productId, warehouseId, StockType.AVAILABLE));
    }

    @Override
    @Transactional
    public Inventory create(final InventoryCreateRequest inventoryCreateRequest) {
        securityService.checkAccessBySellerAuthenticationAndProductOwnership(inventoryCreateRequest.productId());
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

        return inventorySavePort.save(inventory);
    }

    @Override
    @Transactional
    public Inventory update(final Long id, final InventoryUpdateRequest inventoryUpdateRequest) {
        final Inventory inventory = this.getInventoryOrThrow(id);
        securityService.checkAccessByWarehouseId(inventory.getWarehouseId());
        updateMapper.update(inventory, inventoryUpdateRequest);

        return inventorySavePort.save(inventory);
    }

    private Inventory getInventoryOrThrow(final Long id) {
        return inventoryReadPort.findById(id).orElseThrow(() -> InventoryNotFoundException.of(id));
    }

    private boolean existsByProductIdAndWarehouseId(final Long productId, final UUID warehouseId) {
        return inventoryReadPort.findByProductIdAndWarehouseIdAndStockType(productId, warehouseId, StockType.AVAILABLE).isPresent();
    }

}
