package org.robn.ecommerce.order.repository;

import org.robn.ecommerce.order.model.entity.GuestOrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface GuestOrderRepository extends JpaRepository<GuestOrderEntity, UUID> {
}
