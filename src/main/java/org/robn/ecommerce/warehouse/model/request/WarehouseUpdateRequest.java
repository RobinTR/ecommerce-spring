package org.robn.ecommerce.warehouse.model.request;

import jakarta.validation.constraints.Size;
import org.robn.ecommerce.common.util.validation.name.NameWithNumber;
import org.robn.ecommerce.common.util.validation.name.ValidAddress;

public record WarehouseUpdateRequest(

        @Size(max = 50)
        String code,

        @NameWithNumber
        @Size(max = 200)
        String name,

        @ValidAddress
        @Size(max = 1000)
        String fullAddress

) {
}
