package org.robn.ecommerce.cart.model.mapper;

import org.mapstruct.Mapper;
import org.robn.ecommerce.cart.model.CustomerCart;
import org.robn.ecommerce.cart.model.response.CustomerCartResponse;
import org.robn.ecommerce.common.mapper.BaseMapper;

@Mapper(componentModel = "spring")
public interface CustomerCartToResponseMapper extends BaseMapper<CustomerCart, CustomerCartResponse> {
}
