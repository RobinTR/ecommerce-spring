package org.robn.ecommerce.warehouse.model.response;

import java.util.UUID;

public record WarehouseListResponse(

        UUID id,
        String code,
        String name

) {
}
