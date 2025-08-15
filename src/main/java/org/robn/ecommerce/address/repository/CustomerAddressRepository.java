package org.robn.ecommerce.address.repository;

import org.robn.ecommerce.address.model.entity.CustomerAddressEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface CustomerAddressRepository extends JpaRepository<CustomerAddressEntity, UUID> {

    List<CustomerAddressEntity> findByCustomerId(UUID customerId);

}
