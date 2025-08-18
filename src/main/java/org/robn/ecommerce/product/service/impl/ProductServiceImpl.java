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
import org.robn.ecommerce.product.service.ProductImageService;
import org.robn.ecommerce.product.service.ProductService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ProductServiceImpl implements ProductService {

    private final ProductReadPort productReadPort;
    private final ProductSavePort productSavePort;
    private final BrandLookupPort brandLookupPort;
    private final ProductCreateRequestToDomainMapper productCreateRequestToDomainMapper;
    private final ProductUpdateMapper productUpdateMapper;
    private final ProductImageService productImageService;

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
        ensureBrandExists(productCreateRequest.getBrandId());
        final Product product = productCreateRequestToDomainMapper.map(productCreateRequest);
        final Product savedProduct = productSavePort.save(product);
        productImageService.uploadImages(savedProduct.getId(), productCreateRequest.getImageFiles(), productCreateRequest.getAltTexts());
    }

    @Override
    @Transactional
    public void update(final Long id, final ProductUpdateRequest productUpdateRequest) {
        ensureBrandExists(productUpdateRequest.getBrandId());
        final Product product = getProductById(id);
        productUpdateMapper.update(product, productUpdateRequest);
        productSavePort.save(product);
    }

    private Product getProductById(final Long id) {
        return productReadPort.findById(id).orElseThrow(() -> ProductNotFoundException.of(id));
    }

    private void ensureBrandExists(final Long brandId) {
        if (Boolean.FALSE.equals(brandLookupPort.existsById(brandId))) {
            throw RelatedBrandNotFoundException.of(brandId);
        }
    }

}
