package org.robn.ecommerce.address.repository;

import org.robn.ecommerce.address.model.entity.AddressEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AddressRepository extends JpaRepository<AddressEntity, UUID> {
}
