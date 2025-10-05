package org.robn.ecommerce.brand.service.impl;

import lombok.RequiredArgsConstructor;
import org.robn.ecommerce.auth.port.SecurityReadPort;
import org.robn.ecommerce.brand.service.BrandSecurityService;
import org.robn.ecommerce.common.service.BaseSecurityService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BrandSecurityServiceImpl implements BrandSecurityService {

    private final SecurityReadPort securityReadPort;
    private final BaseSecurityService baseSecurityService;

    @Override
    public void requireAdminAuthentication() {
        baseSecurityService.requireAdminAuthentication();
    }

}
