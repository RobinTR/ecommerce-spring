package org.robn.ecommerce.warehouse.model.request;

import jakarta.validation.constraints.Size;
import org.robn.ecommerce.common.util.validation.name.NameWithNumber;
import org.robn.ecommerce.common.util.validation.name.ValidAddress;

public record WarehouseUpdateRequest(

        @Size(max = 50)
        String code,

        @Size(max = 200)
        @NameWithNumber
        String name,

        @Size(max = 1000)
        @ValidAddress
        String fullAddress

) {
}
