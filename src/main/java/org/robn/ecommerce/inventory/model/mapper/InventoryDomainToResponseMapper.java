package org.robn.ecommerce.inventory.model.mapper;

import org.mapstruct.Mapper;
import org.robn.ecommerce.common.mapper.BaseMapper;
import org.robn.ecommerce.inventory.model.Inventory;
import org.robn.ecommerce.inventory.model.response.InventoryResponse;

@Mapper(componentModel = "spring")
public interface InventoryDomainToResponseMapper extends BaseMapper<Inventory, InventoryResponse> {
}
