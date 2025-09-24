package org.robn.ecommerce.warehouse.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.robn.ecommerce.common.model.response.EcoBaseResponse;
import org.robn.ecommerce.warehouse.model.Warehouse;
import org.robn.ecommerce.warehouse.model.mapper.WarehouseDomainToListResponseMapper;
import org.robn.ecommerce.warehouse.model.mapper.WarehouseDomainToResponseMapper;
import org.robn.ecommerce.warehouse.model.request.WarehouseCreateRequest;
import org.robn.ecommerce.warehouse.model.request.WarehouseUpdateRequest;
import org.robn.ecommerce.warehouse.model.response.WarehouseListResponse;
import org.robn.ecommerce.warehouse.model.response.WarehouseResponse;
import org.robn.ecommerce.warehouse.service.WarehouseService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/warehouses")
public class WarehouseController {

    private final WarehouseService warehouseService;
    private final WarehouseDomainToListResponseMapper warehouseDomainToListResponseMapper;
    private final WarehouseDomainToResponseMapper warehouseDomainToResponseMapper;

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public EcoBaseResponse<List<WarehouseListResponse>> findAll() {
        final List<Warehouse> warehouses = warehouseService.findAll();
        final List<WarehouseListResponse> response = warehouseDomainToListResponseMapper.map(warehouses);

        return EcoBaseResponse.successOf(response);
    }

    @GetMapping("/{id}")
    public EcoBaseResponse<WarehouseResponse> findById(@PathVariable final UUID id) {
        final Warehouse warehouse = warehouseService.findById(id);
        final WarehouseResponse response = warehouseDomainToResponseMapper.map(warehouse);

        return EcoBaseResponse.successOf(response);
    }

    @PostMapping
    @PreAuthorize("hasRole('SELLER')")
    public EcoBaseResponse<WarehouseResponse> create(@RequestBody @Valid final WarehouseCreateRequest warehouseCreateRequest) {
        final Warehouse warehouse = warehouseService.create(warehouseCreateRequest);
        final WarehouseResponse response = warehouseDomainToResponseMapper.map(warehouse);

        return EcoBaseResponse.successOf(response);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('SELLER', 'ADMIN')")
    public EcoBaseResponse<WarehouseResponse> update(@PathVariable final UUID id, @RequestBody @Valid final WarehouseUpdateRequest warehouseUpdateRequest) {
        final Warehouse warehouse = warehouseService.update(id, warehouseUpdateRequest);
        final WarehouseResponse response = warehouseDomainToResponseMapper.map(warehouse);

        return EcoBaseResponse.successOf(response);
    }

}
