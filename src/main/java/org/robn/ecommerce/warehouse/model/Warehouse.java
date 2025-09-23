package org.robn.ecommerce.warehouse.model;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.robn.ecommerce.common.model.BaseDomainModel;

import java.util.UUID;

@Getter
@Setter
@SuperBuilder
public class Warehouse extends BaseDomainModel {

    private UUID id;
    private UUID sellerId;
    private String code;
    private String name;

}
