package org.robn.ecommerce.auth.mapper;

import org.mapstruct.Mapper;
import org.robn.ecommerce.auth.model.EcoRole;
import org.robn.ecommerce.auth.model.entity.EcoRoleEntity;
import org.robn.ecommerce.common.mapper.BaseMapper;

@Mapper(componentModel = "spring")
public interface EcoRoleEntityToDomainMapper extends BaseMapper<EcoRoleEntity, EcoRole> {
}
