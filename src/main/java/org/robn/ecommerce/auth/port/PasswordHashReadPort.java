package org.robn.ecommerce.auth.port;

public interface PasswordHashReadPort {

    boolean matches(String plainPassword, String encodedPassword);

}
