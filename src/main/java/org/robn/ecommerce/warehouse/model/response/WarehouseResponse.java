package org.robn.ecommerce.warehouse.model.response;

public record WarehouseResponse(

        Long id,
        String code,
        String name,
        String fullAddress

) {
}
