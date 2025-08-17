package org.robn.ecommerce.warehouse.service.impl;

import lombok.RequiredArgsConstructor;
import org.robn.ecommerce.warehouse.exception.WarehouseNotFoundException;
import org.robn.ecommerce.warehouse.model.Warehouse;
import org.robn.ecommerce.warehouse.model.mapper.WarehouseCreateRequestToDomainMapper;
import org.robn.ecommerce.warehouse.model.mapper.WarehouseUpdateMapper;
import org.robn.ecommerce.warehouse.model.request.WarehouseCreateRequest;
import org.robn.ecommerce.warehouse.model.request.WarehouseUpdateRequest;
import org.robn.ecommerce.warehouse.port.WarehouseReadPort;
import org.robn.ecommerce.warehouse.port.WarehouseSavePort;
import org.robn.ecommerce.warehouse.service.WarehouseService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class WarehouseServiceImpl implements WarehouseService {

    private final WarehouseReadPort warehouseReadPort;
    private final WarehouseSavePort warehouseSavePort;
    private final WarehouseCreateRequestToDomainMapper warehouseCreateRequestToDomainMapper;
    private final WarehouseUpdateMapper warehouseUpdateMapper;

    @Override
    public List<Warehouse> findAll() {
        return warehouseReadPort.findAll();
    }

    @Override
    public Warehouse findById(final Long id) {
        return getWarehouseById(id);
    }

    @Override
    @Transactional
    public void save(final WarehouseCreateRequest warehouseCreateRequest) {
        final Warehouse warehouse = warehouseCreateRequestToDomainMapper.map(warehouseCreateRequest);
        warehouseSavePort.save(warehouse);
    }

    @Override
    @Transactional
    public void update(final Long id, final WarehouseUpdateRequest warehouseUpdateRequest) {
        final Warehouse warehouse = getWarehouseById(id);
        warehouseUpdateMapper.update(warehouse, warehouseUpdateRequest);
        warehouseSavePort.save(warehouse);
    }

    private Warehouse getWarehouseById(final Long id) {
        return warehouseReadPort.findById(id)
                .orElseThrow(() -> new WarehouseNotFoundException(id));
    }

}
