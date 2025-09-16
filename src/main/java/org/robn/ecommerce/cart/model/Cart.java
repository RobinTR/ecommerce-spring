package org.robn.ecommerce.cart.model;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.robn.ecommerce.cart.model.enums.CartStatus;
import org.robn.ecommerce.common.model.BaseDomainModel;

import java.util.UUID;

@Getter
@Setter
@SuperBuilder
public class Cart extends BaseDomainModel {

    private UUID id;
    private CartStatus cartStatus;

}
