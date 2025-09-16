package org.robn.ecommerce.cart.repository;

import org.robn.ecommerce.cart.model.entity.GuestCartEntity;
import org.robn.ecommerce.cart.model.enums.CartStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface GuestCartRepository extends JpaRepository<GuestCartEntity, UUID> {

    List<GuestCartEntity> findAllByGuestId(UUID guestId);

    List<GuestCartEntity> findAllByGuestIdAndCartStatus(UUID guestId, CartStatus cartStatus);

}
