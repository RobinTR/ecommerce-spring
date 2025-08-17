package org.robn.ecommerce.address.model.mapper;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.robn.ecommerce.address.model.SellerAddress;
import org.robn.ecommerce.address.model.request.SellerAddressUpdateRequest;

@Mapper(componentModel = "spring")
public interface SellerAddressUpdateMapper {

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void update(@MappingTarget SellerAddress sellerAddress, SellerAddressUpdateRequest request);

}
