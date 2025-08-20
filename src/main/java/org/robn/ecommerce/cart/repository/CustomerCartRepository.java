package org.robn.ecommerce.cart.repository;

import org.robn.ecommerce.cart.model.entity.CustomerCartEntity;
import org.robn.ecommerce.cart.model.enums.CartStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CustomerCartRepository extends JpaRepository<CustomerCartEntity, UUID> {

    List<CustomerCartEntity> findAllByCustomerId(UUID customerId);

    Optional<CustomerCartEntity> findByCustomerIdAndCartStatus(UUID customerId, CartStatus cartStatus);

}
