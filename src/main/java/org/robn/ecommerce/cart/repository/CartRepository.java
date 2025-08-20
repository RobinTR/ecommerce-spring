package org.robn.ecommerce.cart.repository;

import org.robn.ecommerce.cart.model.entity.CartEntity;
import org.robn.ecommerce.cart.model.enums.CartStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface CartRepository extends JpaRepository<CartEntity, UUID> {

    Optional<CartEntity> findByIdAndCartStatus(UUID id, CartStatus status);

}
