package org.robn.ecommerce.product.service.impl;

import lombok.RequiredArgsConstructor;
import org.robn.ecommerce.product.exception.ProductNotFoundException;
import org.robn.ecommerce.product.exception.RelatedBrandNotFoundException;
import org.robn.ecommerce.product.model.Product;
import org.robn.ecommerce.product.model.mapper.ProductCreateRequestToDomainMapper;
import org.robn.ecommerce.product.model.mapper.ProductUpdateMapper;
import org.robn.ecommerce.product.model.request.ProductCreateRequest;
import org.robn.ecommerce.product.model.request.ProductUpdateRequest;
import org.robn.ecommerce.product.port.BrandLookupPort;
import org.robn.ecommerce.product.port.ProductReadPort;
import org.robn.ecommerce.product.port.ProductSavePort;
import org.robn.ecommerce.product.service.ProductService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ProductServiceImpl implements ProductService {

    private final ProductReadPort productReadPort;
    private final ProductSavePort productSavePort;
    private final BrandLookupPort brandLookupPort;
    private final ProductCreateRequestToDomainMapper productCreateRequestToDomainMapper;
    private final ProductUpdateMapper productUpdateMapper;

    @Override
    public List<Product> findAll() {
        return productReadPort.findAll();
    }

    @Override
    public Product findById(final Long id) {
        return getProductById(id);
    }

    @Override
    @Transactional
    public void create(final ProductCreateRequest productCreateRequest) {
        validateBrandExists(productCreateRequest.getBrandId());
        Product product = productCreateRequestToDomainMapper.map(productCreateRequest);
        productSavePort.save(product);
    }

    @Override
    @Transactional
    public void update(final Long id, final ProductUpdateRequest productUpdateRequest) {
        validateBrandExists(productUpdateRequest.getBrandId());
        Product product = getProductById(id);
        productUpdateMapper.update(product, productUpdateRequest);
        productSavePort.save(product);
    }

    private Product getProductById(final Long id) {
        return productReadPort.findById(id).orElseThrow(() -> new ProductNotFoundException(id));
    }

    private void validateBrandExists(final Long brandId) {
        Optional.of(brandId)
                .filter(brandLookupPort::existsById)
                .orElseThrow(() -> new RelatedBrandNotFoundException(brandId));
    }

}
