package org.robn.ecommerce.product.service;

import org.robn.ecommerce.product.model.ProductImage;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ProductImageService {

    List<ProductImage> findAll();

    List<ProductImage> findAllByProductId(Long productId);

    void uploadImage(Long productId, MultipartFile file, String altText);

    void uploadImages(Long productId, List<MultipartFile> files, List<String> altTexts);

}
