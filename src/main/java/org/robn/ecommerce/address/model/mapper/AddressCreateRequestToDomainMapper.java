package org.robn.ecommerce.address.model.mapper;

import org.mapstruct.Mapper;
import org.robn.ecommerce.address.model.Address;
import org.robn.ecommerce.address.model.request.AddressCreateRequest;
import org.robn.ecommerce.common.mapper.BaseMapper;

@Mapper(componentModel = "spring")
public interface AddressCreateRequestToDomainMapper extends BaseMapper<AddressCreateRequest, Address> {
}
