package org.robn.ecommerce.inventory.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.robn.ecommerce.common.model.response.EcoBaseResponse;
import org.robn.ecommerce.inventory.model.Inventory;
import org.robn.ecommerce.inventory.model.mapper.InventoryDomainToListResponseMapper;
import org.robn.ecommerce.inventory.model.mapper.InventoryDomainToResponseMapper;
import org.robn.ecommerce.inventory.model.request.InventoryCreateRequest;
import org.robn.ecommerce.inventory.model.request.InventoryUpdateRequest;
import org.robn.ecommerce.inventory.model.response.InventoryListResponse;
import org.robn.ecommerce.inventory.model.response.InventoryResponse;
import org.robn.ecommerce.inventory.service.InventoryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/inventories")
public class InventoryController {

    private final InventoryService inventoryService;
    private final InventoryDomainToListResponseMapper domainToListResponseMapper;
    private final InventoryDomainToResponseMapper domainToResponseMapper;

    @GetMapping("/by-product/{productId}")
    public EcoBaseResponse<List<InventoryListResponse>> findAllByProductId(@PathVariable final Long productId) {
        final List<Inventory> inventories = inventoryService.findAllByProductId(productId);

        return EcoBaseResponse.successOf(domainToListResponseMapper.map(inventories));
    }

    @GetMapping("/by-warehouse/{warehouseId}")
    public EcoBaseResponse<List<InventoryListResponse>> findAllByWarehouseId(@PathVariable final Long warehouseId) {
        final List<Inventory> inventories = inventoryService.findAllByWarehouseId(warehouseId);

        return EcoBaseResponse.successOf(domainToListResponseMapper.map(inventories));
    }

    @GetMapping("/{id}")
    public EcoBaseResponse<InventoryResponse> findById(@PathVariable final Long id) {
        final Inventory inventory = inventoryService.findById(id);

        return EcoBaseResponse.successOf(domainToResponseMapper.map(inventory));
    }

    @GetMapping("/by-product-warehouse")
    public EcoBaseResponse<InventoryResponse> findByProductIdAndWarehouseId(
            @RequestParam final Long productId,
            @RequestParam final Long warehouseId) {
        final Inventory inventory = inventoryService.findByProductIdAndWarehouseId(productId, warehouseId);

        return EcoBaseResponse.successOf(domainToResponseMapper.map(inventory));
    }

    @PostMapping
    public EcoBaseResponse<InventoryResponse> create(@RequestBody @Valid final InventoryCreateRequest inventoryCreateRequest) {
        final Inventory inventory = inventoryService.create(inventoryCreateRequest);

        return EcoBaseResponse.successOf(domainToResponseMapper.map(inventory));
    }

    @PutMapping("/{id}")
    public EcoBaseResponse<InventoryResponse> update(
            @PathVariable final Long id,
            @RequestBody @Valid final InventoryUpdateRequest inventoryUpdateRequest) {
        final Inventory inventory = inventoryService.update(id, inventoryUpdateRequest);

        return EcoBaseResponse.successOf(domainToResponseMapper.map(inventory));
    }

}
