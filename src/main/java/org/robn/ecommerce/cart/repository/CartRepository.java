package org.robn.ecommerce.cart.repository;

import org.robn.ecommerce.cart.model.entity.CartEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CartRepository extends JpaRepository<CartEntity, UUID> {
}
