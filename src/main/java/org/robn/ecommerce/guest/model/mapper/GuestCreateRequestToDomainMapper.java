package org.robn.ecommerce.guest.model.mapper;

import org.mapstruct.Mapper;
import org.robn.ecommerce.common.mapper.BaseMapper;
import org.robn.ecommerce.guest.model.Guest;
import org.robn.ecommerce.guest.model.request.GuestCreateRequest;

@Mapper(componentModel = "spring")
public interface GuestCreateRequestToDomainMapper extends BaseMapper<GuestCreateRequest, Guest> {
}
