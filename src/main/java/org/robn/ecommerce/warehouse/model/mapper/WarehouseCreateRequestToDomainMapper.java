package org.robn.ecommerce.warehouse.model.mapper;

import org.mapstruct.Mapper;
import org.robn.ecommerce.common.mapper.BaseMapper;
import org.robn.ecommerce.warehouse.model.Warehouse;
import org.robn.ecommerce.warehouse.model.request.WarehouseCreateRequest;

@Mapper(componentModel = "spring")
public interface WarehouseCreateRequestToDomainMapper extends BaseMapper<WarehouseCreateRequest, Warehouse> {
}
