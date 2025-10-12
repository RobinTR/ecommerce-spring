package org.robn.ecommerce.inventory.service.impl;

import lombok.RequiredArgsConstructor;
import org.robn.ecommerce.auth.exception.EcoAccessDeniedException;
import org.robn.ecommerce.auth.port.SecurityReadPort;
import org.robn.ecommerce.common.service.BaseSecurityService;
import org.robn.ecommerce.inventory.service.InventorySecurityService;
import org.robn.ecommerce.product.model.Product;
import org.robn.ecommerce.product.service.ProductService;
import org.robn.ecommerce.warehouse.model.Warehouse;
import org.robn.ecommerce.warehouse.service.WarehouseService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class InventorySecurityServiceImpl implements InventorySecurityService {

    private final SecurityReadPort securityReadPort;
    private final BaseSecurityService baseSecurityService;
    private final ProductService productService;
    private final WarehouseService warehouseService;

    @Override
    public void checkAccessByProductId(final Long productId) {
        if (baseSecurityService.isAdmin()) {
            return;
        }

        final Product product = productService.findById(productId);

        if (!product.isOwnedBy(securityReadPort.getCurrentUserId())) {
            throw EcoAccessDeniedException.of();
        }
    }

    @Override
    public void checkAccessByWarehouseId(final UUID warehouseId) {
        if (baseSecurityService.isAdmin()) {
            return;
        }

        final Warehouse warehouse = warehouseService.findById(warehouseId);

        if (!warehouse.isOwnedBy(securityReadPort.getCurrentUserId())) {
            throw EcoAccessDeniedException.of();
        }
    }

    @Override
    public void checkAccessBySellerAuthenticationAndProductOwnership(final Long productId) {
        final Product product = productService.findById(productId);

        if (!baseSecurityService.isSeller() || !product.isOwnedBy(securityReadPort.getCurrentUserId())) {
            throw EcoAccessDeniedException.of();
        }
    }

}
