package org.robn.ecommerce.inventory.port;

import org.robn.ecommerce.inventory.model.Inventory;

public interface InventorySavePort {

    Inventory save(Inventory inventory);

}
