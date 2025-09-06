package org.robn.ecommerce.address.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.robn.ecommerce.common.model.BaseDomainModel;

import java.util.UUID;

@Getter
@Setter
@SuperBuilder
public class CustomerAddress extends Address {

    private UUID customerId;

    public boolean isOwnedBy(final UUID targetCustomerId) {
        return this.customerId != null && this.customerId.equals(targetCustomerId);
    }

}
