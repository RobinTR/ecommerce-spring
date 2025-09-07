package org.robn.ecommerce.warehouse.model.response;

public record WarehouseListResponse(

        Long id,
        String code,
        String name,
        String fullAddress

) {
}
