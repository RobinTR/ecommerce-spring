package org.robn.ecommerce.auth.port.adapter;

import lombok.RequiredArgsConstructor;
import org.robn.ecommerce.auth.port.PasswordHashSavePort;
import org.robn.ecommerce.auth.port.PasswordHashReadPort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PasswordHashAdapter implements PasswordHashReadPort, PasswordHashSavePort {

    private final PasswordEncoder passwordEncoder;

    @Override
    public String hashPassword(final String plainPassword) {
        return passwordEncoder.encode(plainPassword);
    }

    @Override
    public boolean matches(final String plainPassword, final String encodedPassword) {
        return passwordEncoder.matches(plainPassword, encodedPassword);
    }

}
