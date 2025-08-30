package org.robn.ecommerce.auth.mapper;

import org.mapstruct.Mapper;
import org.robn.ecommerce.auth.model.EcoToken;
import org.robn.ecommerce.auth.model.response.EcoTokenResponse;
import org.robn.ecommerce.common.mapper.BaseMapper;

@Mapper(componentModel = "spring")
public interface EcoTokenToResponseMapper extends BaseMapper<EcoToken, EcoTokenResponse> {
}
