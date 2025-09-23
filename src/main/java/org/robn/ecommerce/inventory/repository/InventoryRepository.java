package org.robn.ecommerce.inventory.repository;

import org.robn.ecommerce.inventory.model.entity.InventoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface InventoryRepository extends JpaRepository<InventoryEntity, Long> {

    List<InventoryEntity> findAllByProductId(Long productId);

    List<InventoryEntity> findAllByWarehouseId(UUID warehouseId);

    Optional<InventoryEntity> findByProductIdAndWarehouseId(Long productId, UUID warehouseId);

}
