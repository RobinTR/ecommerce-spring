package org.robn.ecommerce.auth.repository;

import org.robn.ecommerce.auth.model.entity.EcoUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface EcoUserRepository extends JpaRepository<EcoUserEntity, UUID> {

    Optional<EcoUserEntity> findByEmail(String email);

    boolean existsByEmail(String email);

}
