package org.robn.ecommerce.inventory.model.mapper;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.robn.ecommerce.inventory.model.Inventory;
import org.robn.ecommerce.inventory.model.request.InventoryUpdateRequest;

@Mapper(componentModel = "spring")
public interface InventoryUpdateMapper {

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void update(@MappingTarget Inventory inventory, InventoryUpdateRequest inventoryUpdateRequest);

}
