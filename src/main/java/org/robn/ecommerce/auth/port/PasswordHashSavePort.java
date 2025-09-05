package org.robn.ecommerce.auth.port;

public interface PasswordHashSavePort {

    String hashPassword(String plainPassword);

}
