package org.robn.ecommerce.brand.repository;

import org.robn.ecommerce.brand.model.entity.BrandEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BrandRepository extends JpaRepository<BrandEntity, Long> {
}
