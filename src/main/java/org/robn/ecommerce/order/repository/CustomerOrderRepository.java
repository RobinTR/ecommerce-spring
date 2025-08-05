package org.robn.ecommerce.order.repository;

import org.robn.ecommerce.order.model.entity.CustomerOrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CustomerOrderRepository extends JpaRepository<CustomerOrderEntity, UUID> {
}
