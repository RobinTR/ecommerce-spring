package org.robn.ecommerce.address.model;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.robn.ecommerce.common.model.BaseDomainModel;

import java.util.UUID;

@Getter
@Setter
@SuperBuilder
public class Address extends BaseDomainModel {

    private UUID id;
    private String title;
    private String firstName;
    private String lastName;
    private String phone;
    private String city;
    private String district;
    private String neighborhood;
    private String fullAddress;
    private Boolean isDefault;

}
