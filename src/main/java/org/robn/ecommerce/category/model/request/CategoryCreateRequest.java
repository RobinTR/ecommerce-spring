package org.robn.ecommerce.category.model.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.robn.ecommerce.common.util.validation.name.NameWithNumber;

@Getter
@Setter
public class CategoryCreateRequest {

    @NameWithNumber
    @NotBlank
    @Size(min = 1, max = 100)
    private String name;

}
