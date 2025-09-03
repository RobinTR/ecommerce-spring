package org.robn.ecommerce.address.model;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.robn.ecommerce.common.model.BaseDomainModel;

import java.util.UUID;

@Getter
@Setter
@SuperBuilder
public class SellerAddress extends BaseDomainModel {

    private UUID id;
    private UUID sellerId;
    private String title;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String city;
    private String district;
    private String neighborhood;
    private String fullAddress;
    private Boolean isDefault;

    public boolean isOwnedBy(final UUID targetSellerId) {
        return this.sellerId != null && this.sellerId.equals(targetSellerId);
    }

}
