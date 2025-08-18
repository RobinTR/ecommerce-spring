package org.robn.ecommerce.inventory.model.mapper;

import org.mapstruct.Mapper;
import org.robn.ecommerce.common.mapper.BaseMapper;
import org.robn.ecommerce.inventory.model.Inventory;
import org.robn.ecommerce.inventory.model.response.InventoryListResponse;

@Mapper(componentModel = "spring")
public interface InventoryDomainToListResponseMapper extends BaseMapper<Inventory, InventoryListResponse> {
}
