package org.robn.ecommerce.order.repository;

import org.robn.ecommerce.order.model.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface OrderRepository extends JpaRepository<OrderEntity, UUID> {
}
