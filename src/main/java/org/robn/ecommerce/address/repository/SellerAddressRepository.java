package org.robn.ecommerce.address.repository;

import org.robn.ecommerce.address.model.entity.SellerAddressEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SellerAddressRepository extends JpaRepository<SellerAddressEntity, UUID> {
}
