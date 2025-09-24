package org.robn.ecommerce.warehouse.service.impl;

import lombok.RequiredArgsConstructor;
import org.robn.ecommerce.auth.exception.EcoAccessDeniedException;
import org.robn.ecommerce.auth.model.enums.Role;
import org.robn.ecommerce.auth.port.SecurityReadPort;
import org.robn.ecommerce.warehouse.exception.WarehouseNotFoundException;
import org.robn.ecommerce.warehouse.model.Warehouse;
import org.robn.ecommerce.warehouse.port.WarehouseReadPort;
import org.robn.ecommerce.warehouse.service.WarehouseSecurityService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class WarehouseSecurityServiceImpl implements WarehouseSecurityService {

    private final WarehouseReadPort warehouseReadPort;
    private final SecurityReadPort securityReadPort;

    @Override
    public void checkAccessByWarehouseId(final UUID warehouseId) {
        final Warehouse warehouse = warehouseReadPort.findById(warehouseId).orElseThrow(() -> WarehouseNotFoundException.of(warehouseId));

        if (!isAdmin() && !isOwner(warehouse)) {
            throw EcoAccessDeniedException.of();
        }
    }

    @Override
    public void checkAccessBySellerId(final UUID sellerId) {
        if (!isAdmin() && !securityReadPort.getCurrentUserId().equals(sellerId)) {
            throw EcoAccessDeniedException.of();
        }
    }

    @Override
    public void requireAdminAccess() {
        if (!isAdmin()) {
            throw EcoAccessDeniedException.of();
        }
    }

    @Override
    public void requireSellerAuthentication() {
        if (!securityReadPort.hasRole(Role.SELLER)) {
            throw EcoAccessDeniedException.of();
        }
    }

    private boolean isAdmin() {
        return securityReadPort.hasRole(Role.ADMIN);
    }

    private boolean isOwner(final Warehouse warehouse) {
        return warehouse.isOwnedBy(securityReadPort.getCurrentUserId());
    }

}
