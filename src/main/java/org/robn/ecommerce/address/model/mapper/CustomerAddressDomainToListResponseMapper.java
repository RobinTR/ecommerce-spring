package org.robn.ecommerce.address.model.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.robn.ecommerce.address.model.CustomerAddress;
import org.robn.ecommerce.address.model.response.CustomerAddressListResponse;
import org.robn.ecommerce.common.mapper.BaseMapper;

@Mapper(componentModel = "spring")
public interface CustomerAddressDomainToListResponseMapper extends BaseMapper<CustomerAddress, CustomerAddressListResponse> {

    @Override
    @Mapping(target = "addressId", source = "id")
    CustomerAddressListResponse map(CustomerAddress customerAddress);

}
