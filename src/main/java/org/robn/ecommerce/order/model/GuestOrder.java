package org.robn.ecommerce.order.model;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.UUID;

@Getter
@Setter
@SuperBuilder
public class GuestOrder extends Order {

    private UUID guestId;

}
