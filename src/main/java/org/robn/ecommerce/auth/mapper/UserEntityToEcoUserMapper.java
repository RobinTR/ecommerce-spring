package org.robn.ecommerce.auth.mapper;

import org.mapstruct.Mapper;
import org.robn.ecommerce.auth.model.EcoUser;
import org.robn.ecommerce.auth.model.entity.EcoUserEntity;
import org.robn.ecommerce.common.mapper.BaseMapper;

@Mapper(componentModel = "spring")
public interface UserEntityToEcoUserMapper extends BaseMapper<EcoUserEntity, EcoUser> {
}
