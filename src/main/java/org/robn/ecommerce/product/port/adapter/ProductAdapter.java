package org.robn.ecommerce.product.port.adapter;

import lombok.RequiredArgsConstructor;
import org.robn.ecommerce.product.mapper.ProductDomainToEntityMapper;
import org.robn.ecommerce.product.mapper.ProductEntityToDomainMapper;
import org.robn.ecommerce.product.model.Product;
import org.robn.ecommerce.product.model.entity.ProductEntity;
import org.robn.ecommerce.product.port.ProductReadPort;
import org.robn.ecommerce.product.port.ProductSavePort;
import org.robn.ecommerce.product.repository.ProductRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ProductAdapter implements ProductReadPort, ProductSavePort {

    private final ProductRepository productRepository;
    private final ProductEntityToDomainMapper productEntityToDomainMapper;
    private final ProductDomainToEntityMapper productDomainToEntityMapper;

    @Override
    public List<Product> findAll() {
        List<ProductEntity> productEntities = productRepository.findAll();

        return productEntityToDomainMapper.map(productEntities);
    }

    @Override
    public Optional<Product> findById(Long id) {
        Optional<ProductEntity> productEntity = productRepository.findById(id);

        return productEntity.map(productEntityToDomainMapper::map);
    }

    @Override
    public Product save(Product product) {
        ProductEntity productEntity = productDomainToEntityMapper.map(product);
        ProductEntity savedProductEntity = productRepository.save(productEntity);

        return productEntityToDomainMapper.map(savedProductEntity);
    }

}
