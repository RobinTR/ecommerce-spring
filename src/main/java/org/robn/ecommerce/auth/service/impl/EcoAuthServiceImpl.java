package org.robn.ecommerce.auth.service.impl;

import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.robn.ecommerce.auth.exception.EcoInvalidEmailOrPasswordException;
import org.robn.ecommerce.auth.exception.EcoInvalidLogoutException;
import org.robn.ecommerce.auth.model.EcoToken;
import org.robn.ecommerce.auth.model.EcoUser;
import org.robn.ecommerce.auth.model.enums.Role;
import org.robn.ecommerce.auth.model.request.EcoLoginRequest;
import org.robn.ecommerce.auth.model.request.EcoLogoutRequest;
import org.robn.ecommerce.auth.port.EcoUserReadPort;
import org.robn.ecommerce.auth.port.PasswordHashReadPort;
import org.robn.ecommerce.auth.port.SecurityReadPort;
import org.robn.ecommerce.auth.service.EcoAuthService;
import org.robn.ecommerce.auth.service.EcoRefreshTokenService;
import org.robn.ecommerce.auth.service.EcoTokenService;
import org.robn.ecommerce.auth.service.RegistrationDomainService;
import org.robn.ecommerce.auth.util.TokenClaimBuilder;
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
    private final SecurityReadPort securityReadPort;
    private final EcoUserReadPort ecoUserReadPort;
    private final CustomerSavePort customerSavePort;
    private final SellerSavePort sellerSavePort;
    private final RegistrationDomainService registrationDomainService;
    private final EcoTokenService ecoTokenService;
    private final EcoRefreshTokenService ecoRefreshTokenService;
    private final CustomerRegisterRequestToDomainMapper customerRegisterRequestToDomainMapper;
    private final SellerRegisterRequestToDomainMapper sellerRegisterRequestToDomainMapper;

    @Override
    @Transactional
    public EcoToken register(final CustomerRegisterRequest customerRegisterRequest) {
        final Customer customer = customerRegisterRequestToDomainMapper.map(customerRegisterRequest);
        registrationDomainService.prepareForRegistration(customer, Role.CUSTOMER);
        final Customer savedCustomer = customerSavePort.save(customer);
        final Claims claims = TokenClaimBuilder.buildClaims(savedCustomer, customerRegisterRequest.deviceId());

        return ecoTokenService.generateToken(claims, customerRegisterRequest.deviceId());
    }

    @Override
    @Transactional
    public EcoToken register(final SellerRegisterRequest sellerRegisterRequest) {
        final Seller seller = sellerRegisterRequestToDomainMapper.map(sellerRegisterRequest);
        registrationDomainService.prepareForRegistration(seller, Role.SELLER);
        final Seller savedSeller = sellerSavePort.save(seller);
        final Claims claims = TokenClaimBuilder.buildClaims(savedSeller, sellerRegisterRequest.deviceId());

        return ecoTokenService.generateToken(claims, sellerRegisterRequest.deviceId());
    }

    @Override
    @Transactional
    public EcoToken login(final EcoLoginRequest ecoLoginRequest) {
        final EcoUser user = ecoUserReadPort.findByEmail(ecoLoginRequest.email()).orElseThrow(EcoInvalidEmailOrPasswordException::of);

        if (!passwordHashReadPort.matches(ecoLoginRequest.password(), user.getPassword())) {
            throw EcoInvalidEmailOrPasswordException.of();
        }

        final Claims claims = TokenClaimBuilder.buildClaims(user, ecoLoginRequest.deviceId());

        return ecoTokenService.generateToken(claims, ecoLoginRequest.deviceId());
    }

    @Override
    @Transactional
    public void logout(final EcoLogoutRequest ecoLogoutRequest) {
        final EcoUser user = ecoUserReadPort.findById(ecoLogoutRequest.userId()).orElseThrow(EcoInvalidEmailOrPasswordException::of);

        if (!user.getId().equals(securityReadPort.getCurrentUserId())) {
            throw EcoInvalidLogoutException.of();
        }

        ecoRefreshTokenService.revokeAllTokensForUserDevice(ecoLogoutRequest.userId(), ecoLogoutRequest.deviceId());
    }

}
