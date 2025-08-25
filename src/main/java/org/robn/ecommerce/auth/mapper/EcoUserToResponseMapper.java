package org.robn.ecommerce.auth.mapper;

import org.mapstruct.Mapper;
import org.robn.ecommerce.auth.model.EcoUser;
import org.robn.ecommerce.auth.model.response.EcoUserResponse;
import org.robn.ecommerce.common.mapper.BaseMapper;

@Mapper(componentModel = "spring")
public interface EcoUserToResponseMapper extends BaseMapper<EcoUser, EcoUserResponse> {
}
