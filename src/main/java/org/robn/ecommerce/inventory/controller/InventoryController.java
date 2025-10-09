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
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/inventories")
public class InventoryController {

    private final InventoryService inventoryService;
    private final InventoryDomainToListResponseMapper domainToListResponseMapper;
    private final InventoryDomainToResponseMapper domainToResponseMapper;

    @GetMapping("/warehouse/{warehouseId}")
    @PreAuthorize("hasAnyRole('ADMIN', 'SELLER')")
    public EcoBaseResponse<List<InventoryListResponse>> findAllByWarehouseId(@PathVariable final UUID warehouseId) {
        final List<Inventory> inventories = inventoryService.findAllByWarehouseId(warehouseId);

        return EcoBaseResponse.successOf(domainToListResponseMapper.map(inventories));
    }

    @GetMapping("/product/{productId}")
    @PreAuthorize("hasAnyRole('ADMIN', 'SELLER')")
    public EcoBaseResponse<List<InventoryListResponse>> findAllByProductId(@PathVariable final Long productId) {
        final List<Inventory> inventories = inventoryService.findAllByProductId(productId);

        return EcoBaseResponse.successOf(domainToListResponseMapper.map(inventories));
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'SELLER')")
    public EcoBaseResponse<InventoryResponse> searchInventory(@RequestParam final Long productId,
                                                              @RequestParam final UUID warehouseId) {
        final Inventory inventory = inventoryService.findAvailableByProductIdAndWarehouseId(productId, warehouseId);

        return EcoBaseResponse.successOf(domainToResponseMapper.map(inventory));
    }

    @PostMapping
    @PreAuthorize("hasRole('SELLER')")
    public EcoBaseResponse<InventoryResponse> create(@RequestBody @Valid final InventoryCreateRequest inventoryCreateRequest) {
        final Inventory inventory = inventoryService.create(inventoryCreateRequest);

        return EcoBaseResponse.successOf(domainToResponseMapper.map(inventory));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'SELLER')")
    public EcoBaseResponse<InventoryResponse> update(@PathVariable final Long id, @RequestBody @Valid final InventoryUpdateRequest inventoryUpdateRequest) {
        final Inventory inventory = inventoryService.update(id, inventoryUpdateRequest);

        return EcoBaseResponse.successOf(domainToResponseMapper.map(inventory));
    }

}
