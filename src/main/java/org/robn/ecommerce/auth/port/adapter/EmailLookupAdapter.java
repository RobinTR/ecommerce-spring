package org.robn.ecommerce.auth.port.adapter;

import lombok.RequiredArgsConstructor;
import org.robn.ecommerce.auth.port.EmailLookupPort;
import org.robn.ecommerce.auth.repository.EcoUserRepository;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EmailLookupAdapter implements EmailLookupPort {

    private final EcoUserRepository ecoUserRepository;

    @Override
    public boolean emailExists(final String email) {
        return ecoUserRepository.existsByEmail(email);
    }

}
