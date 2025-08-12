package org.robn.ecommerce.product.repository;

import org.robn.ecommerce.product.model.entity.ProductImageEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ProductImageRepository extends JpaRepository<ProductImageEntity, UUID> {

    List<ProductImageEntity> findAllByProductId(Long productId);

}
