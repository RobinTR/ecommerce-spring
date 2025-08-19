package org.robn.ecommerce.common.model.owner;

public sealed interface Owner permits GuestOwner, CustomerOwner {

    OwnerType ownerType();

}
