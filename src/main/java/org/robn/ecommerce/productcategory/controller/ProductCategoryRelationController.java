package org.robn.ecommerce.productcategory.controller;

import lombok.RequiredArgsConstructor;
import org.robn.ecommerce.category.model.Category;
import org.robn.ecommerce.category.model.mapper.CategoryDomainToListResponseMapper;
import org.robn.ecommerce.category.model.response.CategoryListResponse;
import org.robn.ecommerce.common.model.response.EcoBaseResponse;
import org.robn.ecommerce.product.model.Product;
import org.robn.ecommerce.product.model.mapper.ProductDomainToListResponseMapper;
import org.robn.ecommerce.product.model.response.ProductListResponse;
import org.robn.ecommerce.productcategory.model.response.CategoryProductsResponse;
import org.robn.ecommerce.productcategory.model.response.ProductCategoriesResponse;
import org.robn.ecommerce.productcategory.service.ProductCategoryService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/product-category-relations")
public class ProductCategoryRelationController {

    private final ProductCategoryService productCategoryService;
    private final CategoryDomainToListResponseMapper categoryDomainToListResponseMapper;
    private final ProductDomainToListResponseMapper productDomainToListResponseMapper;

    @GetMapping("/by-product/{productId}")
    public EcoBaseResponse<ProductCategoriesResponse> findAllCategoriesByProductId(@PathVariable final Long productId) {
        final List<Category> categories = productCategoryService.findAllCategoriesByProductId(productId);
        final List<CategoryListResponse> categoryListResponse = categoryDomainToListResponseMapper.map(categories);

        final ProductCategoriesResponse response = ProductCategoriesResponse.builder()
                .productId(productId)
                .categories(categoryListResponse)
                .build();

        return EcoBaseResponse.successOf(response);
    }

    @GetMapping("/by-category/{categoryId}")
    public EcoBaseResponse<CategoryProductsResponse> findAllProductsByCategoryId(@PathVariable final Long categoryId) {
        final List<Product> products = productCategoryService.findAllProductsByCategoryId(categoryId);
        final List<ProductListResponse> productListResponse = productDomainToListResponseMapper.map(products);

        final CategoryProductsResponse response = CategoryProductsResponse.builder()
                .categoryId(categoryId)
                .products(productListResponse)
                .build();

        return EcoBaseResponse.successOf(response);
    }

}
