package org.robn.ecommerce.cart.repository;

import org.robn.ecommerce.cart.model.entity.CartItemSnapshotEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface CartItemSnapshotRepository extends JpaRepository<CartItemSnapshotEntity, UUID> {

    List<CartItemSnapshotEntity> findAllByCartSnapshotId(UUID cartSnapshotId);

}
