package org.robn.ecommerce.address.model;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.robn.ecommerce.common.model.BaseDomainModel;

import java.util.UUID;

@Getter
@Setter
@SuperBuilder
public class SellerAddress extends Address {

    private UUID sellerId;

    public boolean isOwnedBy(final UUID targetSellerId) {
        return this.sellerId != null && this.sellerId.equals(targetSellerId);
    }

}
