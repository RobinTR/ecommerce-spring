package org.robn.ecommerce.cart.repository;

import org.robn.ecommerce.cart.model.entity.GuestCartEntity;
import org.robn.ecommerce.cart.model.enums.CartStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface GuestCartRepository extends JpaRepository<GuestCartEntity, UUID> {

    List<GuestCartEntity> findAllBySessionId(UUID sessionId);

    Optional<GuestCartEntity> findBySessionIdAndCartStatus(UUID sessionId, CartStatus cartStatus);

}
