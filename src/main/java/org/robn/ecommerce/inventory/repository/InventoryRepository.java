package org.robn.ecommerce.inventory.repository;

import org.robn.ecommerce.inventory.model.entity.InventoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface InventoryRepository extends JpaRepository<InventoryEntity, Long> {

    List<InventoryEntity> findAllByProductId(Long productId);

    List<InventoryEntity> findAllByWarehouseId(Long warehouseId);

    Optional<InventoryEntity> findByProductIdAndWarehouseId(Long productId, Long warehouseId);

}
