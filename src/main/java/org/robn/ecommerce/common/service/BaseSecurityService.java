package org.robn.ecommerce.common.service;

public interface BaseSecurityService {

    boolean isAdmin();

    boolean isCustomer();

    boolean isSeller();

}
