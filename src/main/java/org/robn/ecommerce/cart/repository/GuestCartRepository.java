package org.robn.ecommerce.cart.repository;

import org.robn.ecommerce.cart.model.entity.GuestCartEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface GuestCartRepository extends JpaRepository<GuestCartEntity, UUID> {
}
