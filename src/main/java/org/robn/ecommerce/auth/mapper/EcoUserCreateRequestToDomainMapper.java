package org.robn.ecommerce.auth.mapper;

import org.mapstruct.Mapper;
import org.robn.ecommerce.auth.model.EcoUser;
import org.robn.ecommerce.auth.model.request.EcoUserCreateRequest;
import org.robn.ecommerce.common.mapper.BaseMapper;

@Mapper(componentModel = "spring")
public interface EcoUserCreateRequestToDomainMapper extends BaseMapper<EcoUserCreateRequest, EcoUser> {
}
