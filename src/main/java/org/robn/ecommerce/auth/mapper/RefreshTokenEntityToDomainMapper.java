package org.robn.ecommerce.auth.mapper;

import org.mapstruct.Mapper;
import org.robn.ecommerce.auth.model.RefreshToken;
import org.robn.ecommerce.auth.model.entity.RefreshTokenEntity;
import org.robn.ecommerce.common.mapper.BaseMapper;

@Mapper(componentModel = "spring")
public interface RefreshTokenEntityToDomainMapper extends BaseMapper<RefreshTokenEntity, RefreshToken> {
}
