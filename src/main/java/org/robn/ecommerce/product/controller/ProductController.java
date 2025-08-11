package org.robn.ecommerce.product.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.robn.ecommerce.common.model.response.EcoBaseResponse;
import org.robn.ecommerce.product.model.Product;
import org.robn.ecommerce.product.model.mapper.ProductDomainToListResponseMapper;
import org.robn.ecommerce.product.model.mapper.ProductDomainToResponseMapper;
import org.robn.ecommerce.product.model.request.ProductCreateRequest;
import org.robn.ecommerce.product.model.request.ProductUpdateRequest;
import org.robn.ecommerce.product.model.response.ProductListResponse;
import org.robn.ecommerce.product.model.response.ProductResponse;
import org.robn.ecommerce.product.service.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;
    private final ProductDomainToListResponseMapper productDomainToListResponseMapper;
    private final ProductDomainToResponseMapper productDomainToResponseMapper;

    @GetMapping
    public EcoBaseResponse<List<ProductListResponse>> findAll() {
        List<Product> products = productService.findAll();
        List<ProductListResponse> productsResponse = productDomainToListResponseMapper.map(products);

        return EcoBaseResponse.successOf(productsResponse);
    }

    @GetMapping("/{id}")
    public EcoBaseResponse<ProductResponse> findById(@PathVariable final Long id) {
        Product product = productService.findById(id);
        ProductResponse productResponse = productDomainToResponseMapper.map(product);

        return EcoBaseResponse.successOf(productResponse);
    }

    @PostMapping
    public EcoBaseResponse<Void> create(@RequestBody @Valid final ProductCreateRequest productCreateRequest) {
        productService.create(productCreateRequest);

        return EcoBaseResponse.success();
    }

    @PutMapping("/{id}")
    public EcoBaseResponse<Void> update(@PathVariable final Long id, @RequestBody @Valid final ProductUpdateRequest productUpdateRequest) {
        productService.update(id, productUpdateRequest);

        return EcoBaseResponse.success();
    }

}
