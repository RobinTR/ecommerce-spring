package org.robn.ecommerce.address.model.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.robn.ecommerce.address.model.SellerAddress;
import org.robn.ecommerce.address.model.response.SellerAddressResponse;
import org.robn.ecommerce.common.mapper.BaseMapper;

@Mapper(componentModel = "spring")
public interface SellerAddressDomainToResponseMapper extends BaseMapper<SellerAddress, SellerAddressResponse> {

    @Override
    @Mapping(target = "addressId", source = "id")
    SellerAddressResponse map(SellerAddress source);

}
