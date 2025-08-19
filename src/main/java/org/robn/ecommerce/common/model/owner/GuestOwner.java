package org.robn.ecommerce.common.model.owner;

import java.util.UUID;

public record GuestOwner(UUID sessionId) implements Owner {

    @Override
    public OwnerType ownerType() {
        return OwnerType.GUEST;
    }

}
