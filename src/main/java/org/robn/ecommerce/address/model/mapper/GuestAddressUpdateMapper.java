package org.robn.ecommerce.address.model.mapper;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.robn.ecommerce.address.model.GuestAddress;
import org.robn.ecommerce.address.model.request.GuestAddressUpdateRequest;

@Mapper(componentModel = "spring")
public interface GuestAddressUpdateMapper {

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void update(@MappingTarget GuestAddress guestAddress, GuestAddressUpdateRequest guestAddressUpdateRequest);

}
