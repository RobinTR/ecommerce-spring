package org.robn.ecommerce.address.model.mapper;

import org.mapstruct.Mapper;
import org.robn.ecommerce.address.model.GuestAddress;
import org.robn.ecommerce.address.model.request.GuestAddressCreateRequest;
import org.robn.ecommerce.common.mapper.BaseMapper;

@Mapper(componentModel = "spring")
public interface GuestAddressCreateRequestToDomainMapper extends BaseMapper<GuestAddressCreateRequest, GuestAddress> {
}
