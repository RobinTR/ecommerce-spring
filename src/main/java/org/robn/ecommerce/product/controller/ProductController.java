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
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/products")
public class ProductController {

    private final ProductService productService;
    private final ProductDomainToListResponseMapper productDomainToListResponseMapper;
    private final ProductDomainToResponseMapper productDomainToResponseMapper;

    @GetMapping
    public EcoBaseResponse<List<ProductListResponse>> findAll() {
        final List<Product> products = productService.findAll();
        final List<ProductListResponse> productsResponse = productDomainToListResponseMapper.map(products);

        return EcoBaseResponse.successOf(productsResponse);
    }

    @GetMapping("/{id}")
    public EcoBaseResponse<ProductResponse> findById(@PathVariable final Long id) {
        final Product product = productService.findById(id);
        final ProductResponse productResponse = productDomainToResponseMapper.map(product);

        return EcoBaseResponse.successOf(productResponse);
    }

    @PostMapping(consumes = "multipart/form-data")
    @PreAuthorize("hasRole('SELLER')")
    public EcoBaseResponse<Void> create(@ModelAttribute @Valid final ProductCreateRequest productCreateRequest) {
        productService.create(productCreateRequest);

        return EcoBaseResponse.success();
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or (hasRole('SELLER') and @productSecurity.isOwner(#id, authentication.principal))")
    public EcoBaseResponse<Void> update(@PathVariable final Long id, @RequestBody @Valid final ProductUpdateRequest productUpdateRequest) {
        productService.update(id, productUpdateRequest);

        return EcoBaseResponse.success();
    }

}
