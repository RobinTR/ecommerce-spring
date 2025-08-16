package org.robn.ecommerce.address.repository;

import org.robn.ecommerce.address.model.entity.CustomerAddressEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CustomerAddressRepository extends JpaRepository<CustomerAddressEntity, UUID> {

    List<CustomerAddressEntity> findAllByCustomerId(UUID customerId);

    Optional<CustomerAddressEntity> findByCustomerIdAndId(UUID customerId, UUID addressId);

}
