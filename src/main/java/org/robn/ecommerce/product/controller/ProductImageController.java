package org.robn.ecommerce.product.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.robn.ecommerce.common.model.response.EcoBaseResponse;
import org.robn.ecommerce.product.model.ProductImage;
import org.robn.ecommerce.product.model.mapper.ProductImageDomainToResponseMapper;
import org.robn.ecommerce.product.model.request.ProductImageUploadRequest;
import org.robn.ecommerce.product.model.response.ProductImageResponse;
import org.robn.ecommerce.product.service.ProductImageService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products/{productId}/images")
@RequiredArgsConstructor
public class ProductImageController {

    private final ProductImageService productImageService;
    private final ProductImageDomainToResponseMapper productImageDomainToResponseMapper;

    @GetMapping
    public EcoBaseResponse<List<ProductImageResponse>> findAllByProductId(final @PathVariable("productId") Long productId) {
        final List<ProductImage> productImages = productImageService.findAllByProductId(productId);
        final List<ProductImageResponse> productImagesResponse = productImageDomainToResponseMapper.map(productImages);

        return EcoBaseResponse.successOf(productImagesResponse);
    }

    @PostMapping
    public EcoBaseResponse<Void> uploadImages(
            @PathVariable Long productId,
            @ModelAttribute @Valid ProductImageUploadRequest request
    ) {
        productImageService.uploadImages(productId, request.getImageFiles(), request.getAltTexts());

        return EcoBaseResponse.success();
    }
}
