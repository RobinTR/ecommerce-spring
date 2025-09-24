package org.robn.ecommerce.inventory.port.adapter;

import lombok.RequiredArgsConstructor;
import org.robn.ecommerce.inventory.model.Inventory;
import org.robn.ecommerce.inventory.model.entity.InventoryEntity;
import org.robn.ecommerce.inventory.model.enums.StockType;
import org.robn.ecommerce.inventory.model.mapper.InventoryDomainToEntityMapper;
import org.robn.ecommerce.inventory.model.mapper.InventoryEntityToDomainMapper;
import org.robn.ecommerce.inventory.port.InventoryReadPort;
import org.robn.ecommerce.inventory.port.InventorySavePort;
import org.robn.ecommerce.inventory.repository.InventoryRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class InventoryAdapter implements InventoryReadPort, InventorySavePort {

    private final InventoryRepository inventoryRepository;
    private final InventoryEntityToDomainMapper inventoryEntityToDomainMapper;
    private final InventoryDomainToEntityMapper inventoryDomainToEntityMapper;

    @Override
    public List<Inventory> findAllByProductId(final Long productId) {
        final List<InventoryEntity> inventoryEntities = inventoryRepository.findAllByProductId(productId);

        return inventoryEntityToDomainMapper.map(inventoryEntities);
    }

    @Override
    public List<Inventory> findAllByWarehouseId(final UUID warehouseId) {
        final List<InventoryEntity> inventoryEntities = inventoryRepository.findAllByWarehouseId(warehouseId);

        return inventoryEntityToDomainMapper.map(inventoryEntities);
    }

    @Override
    public Optional<Inventory> findById(final Long id) {
        final Optional<InventoryEntity> inventoryEntity = inventoryRepository.findById(id);

        return inventoryEntity.map(inventoryEntityToDomainMapper::map);
    }

    @Override
    public Optional<Inventory> findByProductIdAndWarehouseIdAndStockType(final Long productId, final UUID warehouseId, final StockType stockType) {
        final Optional<InventoryEntity> inventoryEntity = inventoryRepository.findByProductIdAndWarehouseIdAndStockType(productId, warehouseId, stockType);

        return inventoryEntity.map(inventoryEntityToDomainMapper::map);
    }

    @Override
    public Inventory save(final Inventory inventory) {
        final InventoryEntity inventoryEntity = inventoryDomainToEntityMapper.map(inventory);
        final InventoryEntity savedInventoryEntity = inventoryRepository.save(inventoryEntity);

        return inventoryEntityToDomainMapper.map(savedInventoryEntity);
    }

}
