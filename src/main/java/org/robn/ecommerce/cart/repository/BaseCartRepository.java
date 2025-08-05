package org.robn.ecommerce.cart.repository;

import org.robn.ecommerce.cart.model.entity.BaseCartEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface BaseCartRepository extends JpaRepository<BaseCartEntity, UUID> {
}
