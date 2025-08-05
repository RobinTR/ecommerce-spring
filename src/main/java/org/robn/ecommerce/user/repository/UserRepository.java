package org.robn.ecommerce.user.repository;

import org.robn.ecommerce.user.model.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepository extends JpaRepository<UserEntity, UUID> {
}
