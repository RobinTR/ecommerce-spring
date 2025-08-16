package org.robn.ecommerce.address.model.mapper;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.robn.ecommerce.address.model.CustomerAddress;
import org.robn.ecommerce.address.model.request.CustomerAddressUpdateRequest;

@Mapper(componentModel = "spring")
public interface CustomerAddressUpdateMapper {

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void update(@MappingTarget CustomerAddress customerAddress, CustomerAddressUpdateRequest customerAddressUpdateRequest);

}
