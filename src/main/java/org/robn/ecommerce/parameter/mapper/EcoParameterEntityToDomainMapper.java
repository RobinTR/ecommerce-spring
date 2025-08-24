package org.robn.ecommerce.parameter.mapper;

import org.mapstruct.Mapper;
import org.robn.ecommerce.common.mapper.BaseMapper;
import org.robn.ecommerce.parameter.model.EcoParameter;
import org.robn.ecommerce.parameter.model.entity.EcoParameterEntity;

@Mapper(componentModel = "spring")
public interface EcoParameterEntityToDomainMapper extends BaseMapper<EcoParameterEntity, EcoParameter> {
}
