package org.robn.ecommerce.warehouse.model;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.robn.ecommerce.common.model.BaseDomainModel;

@Getter
@Setter
@SuperBuilder
public class Warehouse extends BaseDomainModel {

    private Long id;
    private String code;
    private String name;
    private String fullAddress;

}
