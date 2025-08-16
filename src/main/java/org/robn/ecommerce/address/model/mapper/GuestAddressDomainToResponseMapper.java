package org.robn.ecommerce.address.model.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.robn.ecommerce.address.model.GuestAddress;
import org.robn.ecommerce.address.model.response.GuestAddressResponse;
import org.robn.ecommerce.common.mapper.BaseMapper;

@Mapper(componentModel = "spring")
public interface GuestAddressDomainToResponseMapper extends BaseMapper<GuestAddress, GuestAddressResponse> {

    @Override
    @Mapping(target = "addressId", source = "id")
    GuestAddressResponse map(GuestAddress source);

}
