package org.robn.ecommerce.productcategory.service.impl;

import lombok.RequiredArgsConstructor;
import org.robn.ecommerce.category.model.Category;
import org.robn.ecommerce.productcategory.model.ProductCategory;
import org.robn.ecommerce.productcategory.model.mapper.ProductCategoryCreateRequestToDomainMapper;
import org.robn.ecommerce.productcategory.model.request.ProductCategoryCreateRequest;
import org.robn.ecommerce.productcategory.port.CategoryLookupPort;
import org.robn.ecommerce.productcategory.port.ProductCategoryDeletePort;
import org.robn.ecommerce.productcategory.port.ProductCategorySavePort;
import org.robn.ecommerce.productcategory.service.ProductCategoryService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ProductCategoryServiceImpl implements ProductCategoryService {

    private final CategoryLookupPort categoryLookupPort;
    private final ProductCategorySavePort productCategorySavePort;
    private final ProductCategoryDeletePort productCategoryDeletePort;
    private final ProductCategoryCreateRequestToDomainMapper productCategoryCreateRequestToDomainMapper;

    @Override
    public List<Category> findAllCategoriesByProductId(Long productId) {
        return categoryLookupPort.findAllCategoriesByProductId(productId);
    }

    @Override
    @Transactional
    public void create(ProductCategoryCreateRequest productCategoryCreateRequest) {
        ProductCategory productCategory = productCategoryCreateRequestToDomainMapper.map(productCategoryCreateRequest);
        productCategorySavePort.save(productCategory);
    }

    @Override
    @Transactional
    public void delete(Long productId, Long categoryId) {
        productCategoryDeletePort.delete(productId, categoryId);
    }

}
