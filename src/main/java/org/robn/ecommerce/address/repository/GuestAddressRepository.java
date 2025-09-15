package org.robn.ecommerce.address.repository;

import org.robn.ecommerce.address.model.entity.GuestAddressEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface GuestAddressRepository extends JpaRepository<GuestAddressEntity, UUID> {

    List<GuestAddressEntity> findAllByGuestDeviceId(String deviceId);

}
