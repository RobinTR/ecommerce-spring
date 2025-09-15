package org.robn.ecommerce.guest.model.repository;

import org.robn.ecommerce.guest.model.entity.GuestEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface GuestRepository extends JpaRepository<GuestEntity, UUID> {

    Optional<GuestEntity> findByDeviceId(String deviceId);

}
