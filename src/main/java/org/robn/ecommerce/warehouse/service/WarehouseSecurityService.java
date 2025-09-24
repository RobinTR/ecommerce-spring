package org.robn.ecommerce.warehouse.service;

import java.util.UUID;

public interface WarehouseSecurityService {

    void checkAccessByWarehouseId(UUID warehouseId);

    void checkAccessBySellerId(UUID sellerId);

    void requireAdminAccess();

    void requireSellerAuthentication();

}
