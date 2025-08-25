package org.robn.ecommerce.auth.repository;

import org.robn.ecommerce.auth.model.entity.EcoRoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface EcoRoleRepository extends JpaRepository<EcoRoleEntity, UUID> {

    Optional<EcoRoleEntity> findByName(String name);

}
