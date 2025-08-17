package org.robn.ecommerce.warehouse.repository;

import org.robn.ecommerce.warehouse.model.entity.WarehouseEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WarehouseRepository extends JpaRepository<WarehouseEntity, Long> {
}
