package org.robn.ecommerce.warehouse.port;

import org.robn.ecommerce.warehouse.model.Warehouse;

public interface WarehouseSavePort {

    Warehouse save(Warehouse warehouse);

}
