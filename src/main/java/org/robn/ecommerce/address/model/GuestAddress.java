package org.robn.ecommerce.address.model;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.robn.ecommerce.common.model.BaseDomainModel;

@Getter
@Setter
@SuperBuilder
public class GuestAddress extends Address {

    private String sessionId;

}
