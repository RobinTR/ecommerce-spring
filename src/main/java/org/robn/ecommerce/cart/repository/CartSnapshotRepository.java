package org.robn.ecommerce.cart.repository;

import org.robn.ecommerce.cart.model.entity.CartSnapshotEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface CartSnapshotRepository extends JpaRepository<CartSnapshotEntity, UUID> {

    List<CartSnapshotEntity> findAllByCartId(UUID cartId);

}
