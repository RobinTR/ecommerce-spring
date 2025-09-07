package org.robn.ecommerce.cart.model;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.UUID;

@Getter
@Setter
@SuperBuilder
public class CustomerCart extends Cart {

    private UUID customerId;

}
