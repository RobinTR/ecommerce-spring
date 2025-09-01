package org.robn.ecommerce.product.port.adapter;

import lombok.RequiredArgsConstructor;
import org.robn.ecommerce.product.model.ProductImage;
import org.robn.ecommerce.product.model.entity.ProductImageEntity;
import org.robn.ecommerce.product.model.mapper.ProductImageDomainToEntityMapper;
import org.robn.ecommerce.product.model.mapper.ProductImageEntityToDomainMapper;
import org.robn.ecommerce.product.port.ProductImageReadPort;
import org.robn.ecommerce.product.port.ProductImageSavePort;
import org.robn.ecommerce.product.repository.ProductImageRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ProductImageAdapter implements ProductImageReadPort, ProductImageSavePort {

    private final ProductImageRepository productImageRepository;
    private final ProductImageEntityToDomainMapper productImageEntityToDomainMapper;
    private final ProductImageDomainToEntityMapper productImageDomainToEntityMapper;

    @Override
    public List<ProductImage> findAll() {
        final List<ProductImageEntity> productImageEntities = productImageRepository.findAll();

        return productImageEntityToDomainMapper.map(productImageEntities);
    }

    @Override
    public List<ProductImage> findAllByProductId(final Long productId) {
        final List<ProductImageEntity> productImageEntities = productImageRepository.findAllByProductId(productId);

        return productImageEntityToDomainMapper.map(productImageEntities);
    }

    @Override
    public Integer countByProductId(final Long productId) {
        return productImageRepository.countByProductId(productId);
    }

    @Override
    public ProductImage save(final ProductImage productImage) {
        final ProductImageEntity productImageEntity = productImageDomainToEntityMapper.map(productImage);
        final ProductImageEntity savedProductEntity = productImageRepository.save(productImageEntity);

        return productImageEntityToDomainMapper.map(savedProductEntity);
    }

}
