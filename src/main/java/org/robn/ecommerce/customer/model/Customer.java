package org.robn.ecommerce.customer.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.robn.ecommerce.auth.model.EcoUser;

@Getter
@Setter
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
public class Customer extends EcoUser {

    private String firstName;
    private String lastName;
    private String phoneNumber;

}
