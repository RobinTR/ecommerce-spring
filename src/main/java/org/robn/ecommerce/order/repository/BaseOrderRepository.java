package org.robn.ecommerce.order.repository;

import org.robn.ecommerce.order.model.entity.BaseOrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface BaseOrderRepository extends JpaRepository<BaseOrderEntity, UUID> {
}
