package org.robn.ecommerce.warehouse.service.impl;

import lombok.RequiredArgsConstructor;
import org.robn.ecommerce.auth.exception.EcoAccessDeniedException;
import org.robn.ecommerce.auth.port.SecurityReadPort;
import org.robn.ecommerce.common.service.BaseSecurityService;
import org.robn.ecommerce.warehouse.exception.WarehouseNotFoundException;
import org.robn.ecommerce.warehouse.model.Warehouse;
import org.robn.ecommerce.warehouse.port.WarehouseReadPort;
import org.robn.ecommerce.warehouse.service.WarehouseSecurityService;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class WarehouseSecurityServiceImpl implements WarehouseSecurityService {

    private final WarehouseReadPort warehouseReadPort;
    private final SecurityReadPort securityReadPort;
    private final BaseSecurityService baseSecurityService;

    @Override
    public void checkAccessByWarehouseId(final UUID warehouseId) {
        final Warehouse warehouse = warehouseReadPort.findById(warehouseId).orElseThrow(() -> WarehouseNotFoundException.of(warehouseId));

        if (!baseSecurityService.isAdmin() && !isOwner(warehouse)) {
            throw EcoAccessDeniedException.of();
        }
    }

    @Override
    public void checkAccessBySellerId(final UUID sellerId) {
        if (!baseSecurityService.isAdmin() && !securityReadPort.getCurrentUserId().equals(sellerId)) {
            throw EcoAccessDeniedException.of();
        }
    }

    @Override
    public void requireAdminAccess() {
        baseSecurityService.requireAdminAccess();
    }

    @Override
    public void requireSellerAuthentication() {
        baseSecurityService.requireSellerAuthentication();
    }

    private boolean isOwner(final Warehouse warehouse) {
        return warehouse.isOwnedBy(securityReadPort.getCurrentUserId());
    }

}
