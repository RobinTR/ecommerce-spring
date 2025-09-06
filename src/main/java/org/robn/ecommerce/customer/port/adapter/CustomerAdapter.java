package org.robn.ecommerce.customer.port.adapter;

import lombok.RequiredArgsConstructor;
import org.robn.ecommerce.customer.model.Customer;
import org.robn.ecommerce.customer.model.entity.CustomerEntity;
import org.robn.ecommerce.customer.model.mapper.CustomerEntityToDomainMapper;
import org.robn.ecommerce.customer.model.mapper.CustomerToEntityMapper;
import org.robn.ecommerce.customer.port.CustomerReadPort;
import org.robn.ecommerce.customer.port.CustomerSavePort;
import org.robn.ecommerce.customer.repository.CustomerRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class CustomerAdapter implements CustomerReadPort, CustomerSavePort {

    private final CustomerRepository customerRepository;
    private final CustomerToEntityMapper customerToEntityMapper;
    private final CustomerEntityToDomainMapper customerEntityToDomainMapper;

    @Override
    public Customer save(final Customer customer) {
        final CustomerEntity customerEntity = customerToEntityMapper.map(customer);
        final CustomerEntity savedCustomerEntity = customerRepository.save(customerEntity);

        return customerEntityToDomainMapper.map(savedCustomerEntity);
    }

    @Override
    public Optional<Customer> findByEmail(final String email) {
        final Optional<CustomerEntity> customerEntity = customerRepository.findByEmail(email);

        return  customerEntity.map(customerEntityToDomainMapper::map);
    }
}
