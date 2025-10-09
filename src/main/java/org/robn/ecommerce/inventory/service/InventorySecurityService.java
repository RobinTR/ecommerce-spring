package org.robn.ecommerce.inventory.service;

import java.util.UUID;

public interface InventorySecurityService {

    void checkAccessByProductId(Long productId);

    void checkAccessByWarehouseId(UUID warehouseId);

    void checkAccessBySellerAuthenticationAndProductOwnership(Long productId);

}
