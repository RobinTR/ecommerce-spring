package org.robn.ecommerce.product.model.request;

import lombok.Getter;
import lombok.Setter;
import org.robn.ecommerce.product.util.validation.ImageList;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Getter
@Setter
public class ProductImageUploadRequest {

    @ImageList
    private List<MultipartFile> imageFiles;

    private List<String> altTexts;

}
