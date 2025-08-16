package org.robn.ecommerce.address.model.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.robn.ecommerce.address.model.GuestAddress;
import org.robn.ecommerce.address.model.response.GuestAddressListResponse;
import org.robn.ecommerce.common.mapper.BaseMapper;

@Mapper(componentModel = "spring")
public interface GuestAddressDomainToListResponseMapper extends BaseMapper<GuestAddress, GuestAddressListResponse> {

    @Override
    @Mapping(target = "addressId", source = "id")
    GuestAddressListResponse map(GuestAddress source);

}
