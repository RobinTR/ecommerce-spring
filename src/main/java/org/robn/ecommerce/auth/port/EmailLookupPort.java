package org.robn.ecommerce.auth.port;

public interface EmailLookupPort {

    boolean emailExists(String email);

}
