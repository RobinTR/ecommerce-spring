package org.robn.ecommerce.address.model.mapper;

import org.mapstruct.Mapper;
import org.robn.ecommerce.address.model.CustomerAddress;
import org.robn.ecommerce.address.model.request.CustomerAddressCreateRequest;
import org.robn.ecommerce.common.mapper.BaseMapper;

@Mapper(componentModel = "spring")
public interface CustomerAddressCreateRequestToDomainMapper extends BaseMapper<CustomerAddressCreateRequest, CustomerAddress> {
}
