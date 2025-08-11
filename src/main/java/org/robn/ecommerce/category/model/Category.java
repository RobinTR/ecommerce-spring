package org.robn.ecommerce.category.model;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.robn.ecommerce.common.model.BaseDomainModel;

@Getter
@Setter
@SuperBuilder
public class Category extends BaseDomainModel {

    private Long id;
    private String name;

}
