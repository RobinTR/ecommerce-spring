package org.robn.ecommerce.cart.model.mapper;

import org.mapstruct.Mapper;
import org.robn.ecommerce.cart.model.GuestCart;
import org.robn.ecommerce.cart.model.response.GuestCartResponse;
import org.robn.ecommerce.common.mapper.BaseMapper;

@Mapper(componentModel = "spring")
public interface GuestCartDomainToResponseMapper extends BaseMapper<GuestCart, GuestCartResponse> {
}
