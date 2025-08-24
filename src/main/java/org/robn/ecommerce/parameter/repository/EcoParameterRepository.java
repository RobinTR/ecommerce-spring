package org.robn.ecommerce.parameter.repository;

import org.robn.ecommerce.parameter.model.entity.EcoParameterEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EcoParameterRepository extends JpaRepository<EcoParameterEntity, Long> {

    Optional<EcoParameterEntity> findByName(String name);

}
