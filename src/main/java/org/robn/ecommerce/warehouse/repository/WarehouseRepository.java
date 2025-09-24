package org.robn.ecommerce.warehouse.repository;

import org.robn.ecommerce.warehouse.model.entity.WarehouseEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface WarehouseRepository extends JpaRepository<WarehouseEntity, UUID> {
}
