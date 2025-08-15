package org.robn.ecommerce.productcategory.port.adapter;

import lombok.RequiredArgsConstructor;
import org.robn.ecommerce.category.model.Category;
import org.robn.ecommerce.category.model.entity.CategoryEntity;
import org.robn.ecommerce.category.model.mapper.CategoryEntityToDomainMapper;
import org.robn.ecommerce.product.model.Product;
import org.robn.ecommerce.product.model.entity.ProductEntity;
import org.robn.ecommerce.product.model.mapper.ProductEntityToDomainMapper;
import org.robn.ecommerce.productcategory.model.ProductCategory;
import org.robn.ecommerce.productcategory.model.entity.ProductCategoryEntity;
import org.robn.ecommerce.productcategory.model.mapper.ProductCategoryDomainToEntityMapper;
import org.robn.ecommerce.productcategory.model.mapper.ProductCategoryEntityToDomainMapper;
import org.robn.ecommerce.productcategory.port.ProductCategoryDeletePort;
import org.robn.ecommerce.productcategory.port.ProductCategoryReadPort;
import org.robn.ecommerce.productcategory.port.ProductCategorySavePort;
import org.robn.ecommerce.productcategory.repository.ProductCategoryRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ProductProductCategoryAdapter implements ProductCategoryReadPort, ProductCategorySavePort, ProductCategoryDeletePort {

    private final ProductCategoryRepository productCategoryRepository;
    private final ProductCategoryEntityToDomainMapper productCategoryEntityToDomainMapper;
    private final ProductCategoryDomainToEntityMapper productCategoryDomainToEntityMapper;
    private final CategoryEntityToDomainMapper categoryEntityToDomainMapper;
    private final ProductEntityToDomainMapper productEntityToDomainMapper;

    @Override
    public void delete(Long productId, Long categoryId) {
        productCategoryRepository.deleteByProductIdAndCategoryId(productId, categoryId);
    }

    @Override
    public List<Category> findAllCategoriesByProductId(Long productId) {
        List<CategoryEntity> entities = productCategoryRepository.findCategoriesByProductId(productId);

        return categoryEntityToDomainMapper.map(entities);
    }

    @Override
    public List<Product> findAllProductsByCategoryId(Long categoryId) {
        List<ProductEntity> entities = productCategoryRepository.findProductsByCategoryId(categoryId);

        return productEntityToDomainMapper.map(entities);
    }

    @Override
    public ProductCategory save(ProductCategory productCategory) {
        ProductCategoryEntity productCategoryEntity = productCategoryDomainToEntityMapper.map(productCategory);
        ProductCategoryEntity savedEntity = productCategoryRepository.save(productCategoryEntity);

        return productCategoryEntityToDomainMapper.map(savedEntity);
    }

}
