package org.robn.ecommerce.warehouse.service.impl;

import lombok.RequiredArgsConstructor;
import org.robn.ecommerce.auth.port.SecurityReadPort;
import org.robn.ecommerce.warehouse.exception.WarehouseNotFoundException;
import org.robn.ecommerce.warehouse.model.Warehouse;
import org.robn.ecommerce.warehouse.model.mapper.WarehouseCreateRequestToDomainMapper;
import org.robn.ecommerce.warehouse.model.mapper.WarehouseUpdateMapper;
import org.robn.ecommerce.warehouse.model.request.WarehouseCreateRequest;
import org.robn.ecommerce.warehouse.model.request.WarehouseUpdateRequest;
import org.robn.ecommerce.warehouse.port.WarehouseReadPort;
import org.robn.ecommerce.warehouse.port.WarehouseSavePort;
import org.robn.ecommerce.warehouse.service.WarehouseSecurityService;
import org.robn.ecommerce.warehouse.service.WarehouseService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class WarehouseServiceImpl implements WarehouseService {

    private final WarehouseReadPort warehouseReadPort;
    private final WarehouseSavePort warehouseSavePort;
    private final SecurityReadPort securityReadPort;
    private final WarehouseCreateRequestToDomainMapper warehouseCreateRequestToDomainMapper;
    private final WarehouseUpdateMapper warehouseUpdateMapper;
    private final WarehouseSecurityService warehouseSecurityService;

    @Override
    public List<Warehouse> findAll() {
        warehouseSecurityService.requireAdminAccess();

        return warehouseReadPort.findAll();
    }

    @Override
    public Warehouse findById(final UUID id) {
        warehouseSecurityService.checkAccessByWarehouseId(id);

        return getWarehouseById(id);
    }

    @Override
    @Transactional
    public Warehouse create(final WarehouseCreateRequest warehouseCreateRequest) {
        warehouseSecurityService.requireSellerAuthentication();
        final Warehouse warehouse = warehouseCreateRequestToDomainMapper.map(warehouseCreateRequest);
        warehouse.setSellerId(securityReadPort.getCurrentUserId());

        return warehouseSavePort.save(warehouse);
    }

    @Override
    @Transactional
    public Warehouse update(final UUID id, final WarehouseUpdateRequest warehouseUpdateRequest) {
        warehouseSecurityService.checkAccessByWarehouseId(id);
        final Warehouse warehouse = getWarehouseById(id);
        warehouseUpdateMapper.update(warehouse, warehouseUpdateRequest);

        return warehouseSavePort.save(warehouse);
    }

    private Warehouse getWarehouseById(final UUID id) {
        return warehouseReadPort.findById(id)
                .orElseThrow(() -> WarehouseNotFoundException.of(id));
    }

}
