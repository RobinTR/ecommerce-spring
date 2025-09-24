package org.robn.ecommerce.warehouse.port;

import org.robn.ecommerce.warehouse.model.Warehouse;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface WarehouseReadPort {

    List<Warehouse> findAll();

    Optional<Warehouse> findById(UUID id);

}
