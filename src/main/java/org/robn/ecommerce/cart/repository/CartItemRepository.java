package org.robn.ecommerce.cart.repository;

import org.robn.ecommerce.cart.model.entity.CartItemEntity;
import org.robn.ecommerce.cart.model.enums.CartItemStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CartItemRepository extends JpaRepository<CartItemEntity, UUID> {

    List<CartItemEntity> findAllByCartId(UUID cartId);

    List<CartItemEntity> findAllByCartIdAndCartItemStatus(UUID cartId, CartItemStatus status);

    Optional<CartItemEntity> findByCartIdAndProductId(UUID cartId, Long productId);

    int countByCartId(UUID cartId);

}
