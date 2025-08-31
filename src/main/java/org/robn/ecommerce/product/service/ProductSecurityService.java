package org.robn.ecommerce.product.service;

import java.util.UUID;

public interface ProductSecurityService {

    boolean isOwner(Long productId, UUID sellerId);

}
