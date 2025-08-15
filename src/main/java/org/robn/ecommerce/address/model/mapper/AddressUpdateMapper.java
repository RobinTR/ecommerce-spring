package org.robn.ecommerce.address.model.mapper;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.robn.ecommerce.address.model.Address;
import org.robn.ecommerce.address.model.request.AddressUpdateRequest;

@Mapper(componentModel = "spring")
public interface AddressUpdateMapper {

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void update(@MappingTarget Address address, AddressUpdateRequest addressUpdateRequest);

}
