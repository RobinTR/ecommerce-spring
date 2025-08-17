package org.robn.ecommerce.warehouse.model.mapper;

import org.mapstruct.Mapper;
import org.robn.ecommerce.common.mapper.BaseMapper;
import org.robn.ecommerce.warehouse.model.Warehouse;
import org.robn.ecommerce.warehouse.model.entity.WarehouseEntity;

@Mapper(componentModel = "spring")
public interface WarehouseEntityToDomainMapper extends BaseMapper<WarehouseEntity, Warehouse> {
}
