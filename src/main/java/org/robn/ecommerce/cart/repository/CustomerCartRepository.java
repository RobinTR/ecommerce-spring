package org.robn.ecommerce.cart.repository;

import org.robn.ecommerce.cart.model.entity.CustomerCartEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CustomerCartRepository extends JpaRepository<CustomerCartEntity, UUID> {
}
