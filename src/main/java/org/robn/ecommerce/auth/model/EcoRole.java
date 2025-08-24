package org.robn.ecommerce.auth.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.robn.ecommerce.common.model.BaseDomainModel;

@Getter
@Setter
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
public class EcoRole extends BaseDomainModel {

    private String id;
    private String name;

}
