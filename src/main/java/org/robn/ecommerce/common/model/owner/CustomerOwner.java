package org.robn.ecommerce.common.model.owner;

import java.util.UUID;

public record CustomerOwner(UUID customerId) implements Owner {

    @Override
    public OwnerType ownerType() {
        return OwnerType.CUSTOMER;
    }

}
