package org.robn.ecommerce.auth.service.impl;

import lombok.RequiredArgsConstructor;
import org.robn.ecommerce.auth.exception.EcoInvalidEmailOrPasswordException;
import org.robn.ecommerce.auth.exception.EcoRoleNotFoundException;
import org.robn.ecommerce.auth.exception.EcoUserAlreadyExistsByEmailException;
import org.robn.ecommerce.auth.model.EcoRole;
import org.robn.ecommerce.auth.model.EcoUser;
import org.robn.ecommerce.auth.model.enums.EcoUserStatus;
import org.robn.ecommerce.auth.model.request.EcoUserCreateRequest;
import org.robn.ecommerce.auth.model.request.EcoUserLoginRequest;
import org.robn.ecommerce.auth.port.EcoRoleReadPort;
import org.robn.ecommerce.auth.port.EcoUserReadPort;
import org.robn.ecommerce.auth.port.EcoUserSavePort;
import org.robn.ecommerce.auth.service.EcoTokenService;
import org.robn.ecommerce.auth.service.EcoUserService;
import org.robn.ecommerce.parameter.port.EcoParameterReadPort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class EcoUserServiceImpl implements EcoUserService {

    private final PasswordEncoder passwordEncoder;
    private final EcoTokenService ecoTokenService;
    private final EcoUserReadPort ecoUserReadPort;
    private final EcoUserSavePort ecoUserSavePort;
    private final EcoParameterReadPort ecoParameterReadPort;
    private final EcoRoleReadPort ecoRoleReadPort;

    @Override
    @Transactional
    public EcoUser register(final EcoUserCreateRequest ecoUserCreateRequest) {
        validateEmail(ecoUserCreateRequest.email());

        final EcoUser user = EcoUser.builder()
                .email(ecoUserCreateRequest.email())
                .password(passwordEncoder.encode(ecoUserCreateRequest.password()))
                .ecoUserStatus(EcoUserStatus.ACTIVE)
                .roles(getDefaultRoles())
                .build();

        return ecoUserSavePort.save(user);
    }

    @Override
    public EcoUser login(EcoUserLoginRequest ecoUserLoginRequest) {
        EcoUser user = ecoUserReadPort.findByEmail(ecoUserLoginRequest.email()).orElseThrow(EcoInvalidEmailOrPasswordException::of);

        if (!passwordEncoder.matches(ecoUserLoginRequest.password(), user.getPassword())) {
            throw EcoInvalidEmailOrPasswordException.of();
        }

        return user;
    }

    private void validateEmail(final String email) {
        if (ecoUserReadPort.emailExists(email)) {
            throw EcoUserAlreadyExistsByEmailException.of(email);
        }
    }

    private List<EcoRole> getDefaultRoles() {
        final String defaultRoleName = ecoParameterReadPort.findByName("DEFAULT_ROLE_NAME").getDefinition();

        return List.of(ecoRoleReadPort.findByName(defaultRoleName).orElseThrow(() -> EcoRoleNotFoundException.of(defaultRoleName)));
    }

}
