package org.robn.ecommerce.cart.repository;

import org.robn.ecommerce.cart.model.entity.CustomerCartEntity;
import org.robn.ecommerce.cart.model.enums.CartStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface CustomerCartRepository extends JpaRepository<CustomerCartEntity, UUID> {

    List<CustomerCartEntity> findAllByCustomerId(UUID customerId);

    List<CustomerCartEntity> findAllByCustomerIdAndCartStatus(UUID customerId, CartStatus cartStatus);

    boolean existsByIdAndCustomerId(UUID cartId, UUID customerId);

}
