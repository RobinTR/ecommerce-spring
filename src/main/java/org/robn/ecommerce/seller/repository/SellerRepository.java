package org.robn.ecommerce.seller.repository;

import org.robn.ecommerce.seller.model.entity.SellerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SellerRepository extends JpaRepository<SellerEntity, UUID> {
}
