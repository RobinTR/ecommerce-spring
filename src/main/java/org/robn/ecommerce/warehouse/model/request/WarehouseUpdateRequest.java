package org.robn.ecommerce.warehouse.model.request;

import jakarta.validation.constraints.Size;
import org.robn.ecommerce.common.util.validation.name.NameWithNumber;

public record WarehouseUpdateRequest(

        @Size(max = 50)
        String code,

        @NameWithNumber
        @Size(max = 200)
        String name

) {
}
