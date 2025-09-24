package org.robn.ecommerce.warehouse.port.adapter;

import lombok.RequiredArgsConstructor;
import org.robn.ecommerce.warehouse.model.Warehouse;
import org.robn.ecommerce.warehouse.model.entity.WarehouseEntity;
import org.robn.ecommerce.warehouse.model.mapper.WarehouseDomainToEntityMapper;
import org.robn.ecommerce.warehouse.model.mapper.WarehouseEntityToDomainMapper;
import org.robn.ecommerce.warehouse.port.WarehouseReadPort;
import org.robn.ecommerce.warehouse.port.WarehouseSavePort;
import org.robn.ecommerce.warehouse.repository.WarehouseRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class WarehouseAdapter implements WarehouseReadPort, WarehouseSavePort {

    private final WarehouseRepository warehouseRepository;
    private final WarehouseEntityToDomainMapper warehouseEntityToDomainMapper;
    private final WarehouseDomainToEntityMapper warehouseDomainToEntityMapper;

    @Override
    public List<Warehouse> findAll() {
        final List<WarehouseEntity> warehouseEntities = warehouseRepository.findAll();

        return warehouseEntityToDomainMapper.map(warehouseEntities);
    }

    @Override
    public Optional<Warehouse> findById(final UUID id) {
        final Optional<WarehouseEntity> warehouse = warehouseRepository.findById(id);

        return warehouse.map(warehouseEntityToDomainMapper::map);
    }

    @Override
    public Warehouse save(final Warehouse warehouse) {
        final WarehouseEntity warehouseEntity = warehouseDomainToEntityMapper.map(warehouse);
        final WarehouseEntity savedWarehouseEntity = warehouseRepository.save(warehouseEntity);

        return warehouseEntityToDomainMapper.map(savedWarehouseEntity);
    }

}
