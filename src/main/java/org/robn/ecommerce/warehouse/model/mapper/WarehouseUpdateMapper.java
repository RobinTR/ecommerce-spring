package org.robn.ecommerce.warehouse.model.mapper;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.robn.ecommerce.warehouse.model.Warehouse;
import org.robn.ecommerce.warehouse.model.request.WarehouseUpdateRequest;

@Mapper(componentModel = "spring")
public interface WarehouseUpdateMapper {

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void update(@MappingTarget Warehouse warehouse, WarehouseUpdateRequest warehouseUpdateRequest);

}
