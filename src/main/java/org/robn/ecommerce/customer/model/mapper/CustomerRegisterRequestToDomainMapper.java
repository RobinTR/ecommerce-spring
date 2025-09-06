package org.robn.ecommerce.customer.model.mapper;

import org.mapstruct.Mapper;
import org.robn.ecommerce.common.mapper.BaseMapper;
import org.robn.ecommerce.customer.model.Customer;
import org.robn.ecommerce.customer.model.request.CustomerRegisterRequest;

@Mapper(componentModel = "spring")
public interface CustomerRegisterRequestToDomainMapper extends BaseMapper<CustomerRegisterRequest, Customer> {
}
