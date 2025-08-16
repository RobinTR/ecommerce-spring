package org.robn.ecommerce.address.model.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.robn.ecommerce.address.model.CustomerAddress;
import org.robn.ecommerce.address.model.response.CustomerAddressResponse;
import org.robn.ecommerce.common.mapper.BaseMapper;

@Mapper(componentModel = "spring")
public interface CustomerAddressDomainToResponseMapper extends BaseMapper<CustomerAddress, CustomerAddressResponse> {

    @Override
    @Mapping(target = "addressId", source = "id")
    CustomerAddressResponse map(CustomerAddress customerAddress);

}
