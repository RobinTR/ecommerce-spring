package org.robn.ecommerce.warehouse.model.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WarehouseListResponse {

    private Long id;
    private String code;
    private String name;
    private String fullAddress;

}
