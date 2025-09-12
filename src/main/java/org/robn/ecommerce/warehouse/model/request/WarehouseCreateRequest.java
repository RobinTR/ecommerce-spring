package org.robn.ecommerce.warehouse.model.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.robn.ecommerce.common.util.validation.name.NameWithNumber;
import org.robn.ecommerce.common.util.validation.name.ValidAddress;

public record WarehouseCreateRequest(

        @NotBlank
        @Size(max = 50)
        String code,

        @NotBlank
        @NameWithNumber
        @Size(max = 200)
        String name,

        @NotBlank
        @ValidAddress
        @Size(max = 1000)
        String fullAddress

) {
}
