package org.robn.ecommerce.warehouse.service;

import org.robn.ecommerce.warehouse.model.Warehouse;
import org.robn.ecommerce.warehouse.model.request.WarehouseCreateRequest;
import org.robn.ecommerce.warehouse.model.request.WarehouseUpdateRequest;

import java.util.List;

public interface WarehouseService {

    List<Warehouse> findAll();

    Warehouse findById(Long id);

    void save(WarehouseCreateRequest warehouseCreateRequest);

    void update(Long id, WarehouseUpdateRequest warehouseUpdateRequest);

}
