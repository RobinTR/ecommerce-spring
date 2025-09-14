package org.robn.ecommerce.product.model.request;

import jakarta.validation.constraints.*;
import org.robn.ecommerce.common.util.validation.name.NameWithNumber;
import org.robn.ecommerce.product.util.validation.ImageList;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.util.List;

public record ProductCreateRequest(

        @NotNull
        @Positive
        Long brandId,

        @NotBlank
        @NameWithNumber
        @Size(max = 200)
        String name,

        @NotBlank
        @Size(max = 2000)
        String description,

        @DecimalMin(value = "10")
        @Digits(integer = 17, fraction = 2)
        BigDecimal price,

        @NotNull
        Boolean isActive,

        @NotNull
        Boolean isVerified,

        @ImageList
        List<MultipartFile> imageFiles,

        @Size(max = 10)
        List<String> altTexts

) {
}
