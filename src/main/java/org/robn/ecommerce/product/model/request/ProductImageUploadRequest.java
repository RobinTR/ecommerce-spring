package org.robn.ecommerce.product.model.request;

import org.robn.ecommerce.product.util.validation.ImageList;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public record ProductImageUploadRequest(

        @ImageList
        List<MultipartFile> imageFiles,

        List<String> altTexts

) {
}
