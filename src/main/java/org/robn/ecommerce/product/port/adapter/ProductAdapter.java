package org.robn.ecommerce.product.port.adapter;

import lombok.RequiredArgsConstructor;
import org.robn.ecommerce.product.model.mapper.ProductDomainToEntityMapper;
import org.robn.ecommerce.product.model.mapper.ProductEntityToDomainMapper;
import org.robn.ecommerce.product.model.Product;
import org.robn.ecommerce.product.model.entity.ProductEntity;
import org.robn.ecommerce.product.port.ProductLookupPort;
import org.robn.ecommerce.product.port.ProductReadPort;
import org.robn.ecommerce.product.port.ProductSavePort;
import org.robn.ecommerce.product.repository.ProductRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ProductAdapter implements ProductReadPort, ProductSavePort, ProductLookupPort {

    private final ProductRepository productRepository;
    private final ProductEntityToDomainMapper productEntityToDomainMapper;
    private final ProductDomainToEntityMapper productDomainToEntityMapper;

    @Override
    public List<Product> findAll() {
        final List<ProductEntity> productEntities = productRepository.findAll();

        return productEntityToDomainMapper.map(productEntities);
    }

    @Override
    public List<Product> findAllByIds(final List<Long> ids) {
        final List<ProductEntity> productEntities = productRepository.findAllById(ids);

        return productEntityToDomainMapper.map(productEntities);
    }

    @Override
    public Optional<Product> findById(final Long id) {
        final Optional<ProductEntity> productEntity = productRepository.findById(id);

        return productEntity.map(productEntityToDomainMapper::map);
    }

    @Override
    public Product save(final Product product) {
        final ProductEntity productEntity = productDomainToEntityMapper.map(product);
        final ProductEntity savedProductEntity = productRepository.save(productEntity);

        return productEntityToDomainMapper.map(savedProductEntity);
    }

    @Override
    public Boolean existsById(Long id) {
        return productRepository.existsById(id);
    }

}
