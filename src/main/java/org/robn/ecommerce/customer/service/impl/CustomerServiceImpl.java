package org.robn.ecommerce.customer.service.impl;

import lombok.RequiredArgsConstructor;
import org.robn.ecommerce.auth.model.EcoToken;
import org.robn.ecommerce.auth.model.enums.Role;
import org.robn.ecommerce.auth.service.EcoTokenService;
import org.robn.ecommerce.auth.service.RegistrationDomainService;
import org.robn.ecommerce.auth.util.TokenClaimBuilder;
import org.robn.ecommerce.customer.model.Customer;
import org.robn.ecommerce.customer.model.mapper.CustomerRegisterRequestToDomainMapper;
import org.robn.ecommerce.customer.model.request.CustomerRegisterRequest;
import org.robn.ecommerce.customer.port.CustomerSavePort;
import org.robn.ecommerce.customer.service.CustomerService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CustomerServiceImpl implements CustomerService {

    private final CustomerSavePort customerSavePort;
    private final EcoTokenService ecoTokenService;
    private final RegistrationDomainService registrationDomainService;
    private final CustomerRegisterRequestToDomainMapper customerRegisterRequestToDomainMapper;

    @Override
    @Transactional
    public EcoToken register(final CustomerRegisterRequest request) {

        final Customer customer = customerRegisterRequestToDomainMapper.map(request);
        registrationDomainService.prepareForRegistration(customer, Role.CUSTOMER);

        final Customer savedCustomer = customerSavePort.save(customer);

        return ecoTokenService.generateToken(TokenClaimBuilder.buildClaims(savedCustomer, request.deviceId()), request.deviceId());
    }

}
