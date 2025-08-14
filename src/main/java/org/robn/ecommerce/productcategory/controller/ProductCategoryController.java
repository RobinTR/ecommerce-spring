package org.robn.ecommerce.productcategory.controller;

import lombok.RequiredArgsConstructor;
import org.robn.ecommerce.category.model.Category;
import org.robn.ecommerce.category.model.mapper.CategoryDomainToResponseMapper;
import org.robn.ecommerce.category.model.response.CategoryResponse;
import org.robn.ecommerce.common.model.response.EcoBaseResponse;
import org.robn.ecommerce.productcategory.model.mapper.ProductCategoryToProductCategoriesResponseMap;
import org.robn.ecommerce.productcategory.model.response.ProductCategoriesResponse;
import org.robn.ecommerce.productcategory.service.ProductCategoryService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class ProductCategoryController {

    private final ProductCategoryService productCategoryService;
    private final CategoryDomainToResponseMapper categoryDomainToResponseMapper;
    private final ProductCategoryToProductCategoriesResponseMap productCategoryToProductCategoriesResponseMap;


    @GetMapping("/products/{productId}/categories")
    public EcoBaseResponse<ProductCategoriesResponse> findAllByProductId(@PathVariable final Long productId) {
        List<Category> categories = productCategoryService.findAllCategoriesByProductId(productId);
        List<CategoryResponse> categoriesResponse = categoryDomainToResponseMapper.map(categories);
        ProductCategoriesResponse response = productCategoryToProductCategoriesResponseMap.map(productId, categoriesResponse);

        return EcoBaseResponse.successOf(response);
    }

}
