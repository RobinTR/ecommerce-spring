package org.robn.ecommerce.auth.service.impl;

import lombok.RequiredArgsConstructor;
import org.robn.ecommerce.auth.exception.EcoRoleNotFoundException;
import org.robn.ecommerce.auth.exception.EcoUserAlreadyExistsByEmailException;
import org.robn.ecommerce.auth.model.EcoRole;
import org.robn.ecommerce.auth.model.EcoUser;
import org.robn.ecommerce.auth.model.enums.EcoUserStatus;
import org.robn.ecommerce.auth.model.enums.Role;
import org.robn.ecommerce.auth.port.EcoRoleReadPort;
import org.robn.ecommerce.auth.port.EmailLookupPort;
import org.robn.ecommerce.auth.port.PasswordHashSavePort;
import org.robn.ecommerce.auth.service.RegistrationDomainService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class RegistrationDomainServiceImpl implements RegistrationDomainService {

    private final EmailLookupPort emailLookupPort;
    private final EcoRoleReadPort ecoRoleReadPort;
    private final PasswordHashSavePort passwordHashSavePort;

    @Override
    public void prepareForRegistration(final EcoUser user, final Role role) {
        ensureEmailIsUnique(user.getEmail());
        user.setEcoUserStatus(EcoUserStatus.ACTIVE);
        user.setPassword(passwordHashSavePort.hashPassword(user.getPassword()));
        user.setRoles(getRoles(role));
    }

    private void ensureEmailIsUnique(final String email) {
        if (emailLookupPort.emailExists(email)) {
            throw EcoUserAlreadyExistsByEmailException.of(email);
        }
    }

    private List<EcoRole> getRoles(final Role role) {
        return role.getAssignedRoles().stream()
                .map(roleName -> ecoRoleReadPort.findByName(roleName)
                        .orElseThrow(() -> EcoRoleNotFoundException.of(roleName))).toList();
    }

}
