package org.robn.ecommerce.warehouse.service;

import org.robn.ecommerce.warehouse.model.Warehouse;
import org.robn.ecommerce.warehouse.model.request.WarehouseCreateRequest;
import org.robn.ecommerce.warehouse.model.request.WarehouseUpdateRequest;

import java.util.List;
import java.util.UUID;

public interface WarehouseService {

    List<Warehouse> findAll();

    Warehouse findById(UUID id);

    Warehouse save(WarehouseCreateRequest warehouseCreateRequest);

    Warehouse update(UUID id, WarehouseUpdateRequest warehouseUpdateRequest);

}
