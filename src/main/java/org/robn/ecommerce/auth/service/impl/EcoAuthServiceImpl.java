package org.robn.ecommerce.auth.service.impl;

import lombok.RequiredArgsConstructor;
import org.robn.ecommerce.auth.exception.EcoInvalidEmailOrPasswordException;
import org.robn.ecommerce.auth.model.EcoToken;
import org.robn.ecommerce.auth.model.EcoUser;
import org.robn.ecommerce.auth.model.enums.Role;
import org.robn.ecommerce.auth.model.request.EcoLoginRequest;
import org.robn.ecommerce.auth.port.EcoUserReadPort;
import org.robn.ecommerce.auth.port.PasswordHashReadPort;
import org.robn.ecommerce.auth.service.EcoAuthService;
import org.robn.ecommerce.auth.service.EcoTokenService;
import org.robn.ecommerce.auth.service.RegistrationDomainService;
import org.robn.ecommerce.customer.model.Customer;
import org.robn.ecommerce.customer.model.mapper.CustomerRegisterRequestToDomainMapper;
import org.robn.ecommerce.customer.model.request.CustomerRegisterRequest;
import org.robn.ecommerce.customer.port.CustomerSavePort;
import org.robn.ecommerce.seller.model.Seller;
import org.robn.ecommerce.seller.model.mapper.SellerRegisterRequestToDomainMapper;
import org.robn.ecommerce.seller.model.request.SellerRegisterRequest;
import org.robn.ecommerce.seller.port.SellerSavePort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class EcoAuthServiceImpl implements EcoAuthService {

    private final PasswordHashReadPort passwordHashReadPort;
    private final EcoUserReadPort ecoUserReadPort;
    private final CustomerSavePort customerSavePort;
    private final SellerSavePort sellerSavePort;
    private final RegistrationDomainService registrationDomainService;
    private final EcoTokenService ecoTokenService;
    private final CustomerRegisterRequestToDomainMapper customerRegisterRequestToDomainMapper;
    private final SellerRegisterRequestToDomainMapper sellerRegisterRequestToDomainMapper;

    @Override
    @Transactional
    public EcoToken register(final CustomerRegisterRequest customerRegisterRequest) {
        final Customer customer = customerRegisterRequestToDomainMapper.map(customerRegisterRequest);
        registrationDomainService.prepareForRegistration(customer, Role.CUSTOMER);
        final Customer savedCustomer = customerSavePort.save(customer);

        return ecoTokenService.generateToken(savedCustomer.getClaims());
    }

    @Override
    @Transactional
    public EcoToken register(final SellerRegisterRequest sellerRegisterRequest) {
        final Seller seller = sellerRegisterRequestToDomainMapper.map(sellerRegisterRequest);
        registrationDomainService.prepareForRegistration(seller, Role.SELLER);
        final Seller savedSeller = sellerSavePort.save(seller);

        return ecoTokenService.generateToken(savedSeller.getClaims());
    }

    @Override
    public EcoToken login(final EcoLoginRequest ecoLoginRequest) {
        final EcoUser user = ecoUserReadPort.findByEmail(ecoLoginRequest.email()).orElseThrow(EcoInvalidEmailOrPasswordException::of);

        if (!passwordHashReadPort.matches(ecoLoginRequest.password(), user.getPassword())) {
            throw EcoInvalidEmailOrPasswordException.of();
        }

        return ecoTokenService.generateToken(user.getClaims());
    }

}
