package org.robn.ecommerce.guest.model.mapper;

import org.mapstruct.Mapper;
import org.robn.ecommerce.common.mapper.BaseMapper;
import org.robn.ecommerce.guest.model.Guest;
import org.robn.ecommerce.guest.model.entity.GuestEntity;

@Mapper(componentModel = "spring")
public interface GuestEntityToDomainMapper extends BaseMapper<GuestEntity, Guest> {
}
