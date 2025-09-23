package org.robn.ecommerce.warehouse.model.response;

import java.util.UUID;

public record WarehouseResponse(

        UUID id,
        String code,
        String name,
        String fullAddress

) {
}
