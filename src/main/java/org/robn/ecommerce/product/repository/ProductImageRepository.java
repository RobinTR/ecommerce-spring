package org.robn.ecommerce.product.repository;

import org.robn.ecommerce.product.model.entity.ProductImageEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProductImageRepository extends JpaRepository<ProductImageEntity, UUID> {
}
