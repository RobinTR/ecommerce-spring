package org.robn.ecommerce.order.model;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.UUID;

@Getter
@Setter
@SuperBuilder
public class CustomerOrder extends Order {

    private UUID customerId;

}
