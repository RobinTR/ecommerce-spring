package org.robn.ecommerce.product.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.robn.ecommerce.common.model.response.EcoBaseResponse;
import org.robn.ecommerce.product.model.ProductImage;
import org.robn.ecommerce.product.model.mapper.ProductImageDomainToResponseMapper;
import org.robn.ecommerce.product.model.request.ProductImageUploadRequest;
import org.robn.ecommerce.product.model.response.ProductImageResponse;
import org.robn.ecommerce.product.service.ProductImageService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/products/{productId}/images")
public class ProductImageController {

    private final ProductImageService productImageService;
    private final ProductImageDomainToResponseMapper productImageDomainToResponseMapper;

    @GetMapping
    @PreAuthorize("permitAll()")
    public EcoBaseResponse<List<ProductImageResponse>> findAllByProductId(@PathVariable("productId") final Long productId) {
        final List<ProductImage> productImages = productImageService.findAllByProductId(productId);
        final List<ProductImageResponse> productImagesResponse = productImageDomainToResponseMapper.map(productImages);

        return EcoBaseResponse.successOf(productImagesResponse);
    }

    @PostMapping(consumes = "multipart/form-data")
    @PreAuthorize("hasAnyRole('ADMIN', 'SELLER')")
    public EcoBaseResponse<Void> uploadImages(
            @PathVariable final Long productId,
            @ModelAttribute @Valid final ProductImageUploadRequest request
    ) {
        productImageService.uploadImages(productId, request.getImageFiles(), request.getAltTexts());

        return EcoBaseResponse.success();
    }

}
