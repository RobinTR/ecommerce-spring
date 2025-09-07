package org.robn.ecommerce.address.model.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.robn.ecommerce.address.model.SellerAddress;
import org.robn.ecommerce.address.model.response.SellerAddressListResponse;
import org.robn.ecommerce.common.mapper.BaseMapper;

@Mapper(componentModel = "spring")
public interface SellerAddressDomainToListResponseMapper extends BaseMapper<SellerAddress, SellerAddressListResponse> {

    @Override
    @Mapping(target = "addressId", source = "id")
    SellerAddressListResponse map(SellerAddress source);

}
