package org.robn.ecommerce.warehouse.model.request;

import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.robn.ecommerce.common.util.validation.name.NameWithNumber;
import org.robn.ecommerce.common.util.validation.name.ValidAddress;

@Getter
@Setter
public class WarehouseUpdateRequest {

    @Size(max = 50)
    private String code;

    @Size(max = 200)
    @NameWithNumber
    private String name;

    @Size(max = 1000)
    @ValidAddress
    private String fullAddress;

}
