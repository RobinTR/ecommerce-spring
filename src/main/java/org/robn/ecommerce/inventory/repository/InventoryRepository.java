package org.robn.ecommerce.inventory.repository;

import org.robn.ecommerce.inventory.model.entity.InventoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface InventoryRepository extends JpaRepository<InventoryEntity, UUID> {
}
