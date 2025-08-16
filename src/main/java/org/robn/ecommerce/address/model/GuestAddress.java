package org.robn.ecommerce.address.model;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.robn.ecommerce.common.model.BaseDomainModel;

@Getter
@Setter
@SuperBuilder
public class GuestAddress extends BaseDomainModel {

    private String id;
    private String sessionId;
    private String title;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String city;
    private String district;
    private String neighborhood;
    private String fullAddress;
    private Boolean isDefault;

}
