package org.robn.ecommerce.brand.model;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.robn.ecommerce.common.model.BaseDomainModel;

@Getter
@Setter
@SuperBuilder
public class Brand extends BaseDomainModel {

    private Long id;
    private String name;

}
