package org.robn.ecommerce.productcategory.service.impl;

import lombok.RequiredArgsConstructor;
import org.robn.ecommerce.category.model.Category;
import org.robn.ecommerce.product.model.Product;
import org.robn.ecommerce.productcategory.model.ProductCategory;
import org.robn.ecommerce.productcategory.model.mapper.ProductCategoryCreateRequestToDomainMapper;
import org.robn.ecommerce.productcategory.model.request.ProductCategoryCreateRequest;
import org.robn.ecommerce.productcategory.port.ProductCategoryDeletePort;
import org.robn.ecommerce.productcategory.port.ProductCategoryReadPort;
import org.robn.ecommerce.productcategory.port.ProductCategorySavePort;
import org.robn.ecommerce.productcategory.service.ProductCategorySecurityService;
import org.robn.ecommerce.productcategory.service.ProductCategoryService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ProductCategoryServiceImpl implements ProductCategoryService {

    private final ProductCategoryReadPort productCategoryReadPort;
    private final ProductCategorySavePort productCategorySavePort;
    private final ProductCategoryDeletePort productCategoryDeletePort;
    private final ProductCategoryCreateRequestToDomainMapper productCategoryCreateRequestToDomainMapper;
    private final ProductCategorySecurityService productCategorySecurityService;

    @Override
    public List<Category> findAllCategoriesByProductId(final Long productId) {
        return productCategoryReadPort.findAllCategoriesByProductId(productId);
    }

    @Override
    public List<Product> findAllProductsByCategoryId(final Long categoryId) {
        return productCategoryReadPort.findAllProductsByCategoryId(categoryId);
    }

    @Override
    @Transactional
    public void create(final ProductCategoryCreateRequest productCategoryCreateRequest) {
        productCategorySecurityService.checkAccessByProductId(productCategoryCreateRequest.productId());
        final ProductCategory productCategory = productCategoryCreateRequestToDomainMapper.map(productCategoryCreateRequest);
        productCategorySavePort.save(productCategory);
    }

    @Override
    @Transactional
    public void delete(final Long productId, final Long categoryId) {
        productCategorySecurityService.checkAccessByProductId(productId);
        productCategoryDeletePort.delete(productId, categoryId);
    }

}
