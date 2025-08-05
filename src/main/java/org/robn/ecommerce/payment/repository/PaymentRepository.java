package org.robn.ecommerce.payment.repository;

import org.robn.ecommerce.payment.model.entity.PaymentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PaymentRepository extends JpaRepository<PaymentEntity, UUID> {
}
