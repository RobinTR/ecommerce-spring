package org.robn.ecommerce.product.model.request;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;
import org.robn.ecommerce.common.util.validation.name.NameWithNumber;
import org.robn.ecommerce.product.util.validation.ImageList;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
public class ProductCreateRequest {

    @NotNull
    @Positive
    private Long brandId;

    @NameWithNumber
    @NotBlank
    @Size(max = 100)
    private String name;

    @NotBlank
    @Size(max = 2000)
    private String description;

    @DecimalMin(value = "10")
    @Digits(integer = 17, fraction = 2)
    private BigDecimal price;

    @NotNull
    private Boolean isActive;

    @NotNull
    private Boolean isVerified;

    @ImageList
    private List<MultipartFile> imageFiles;

    @Size(max = 10)
    private List<String> altTexts;

}
