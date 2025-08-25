package org.robn.ecommerce.auth.port;

import org.robn.ecommerce.auth.model.EcoUser;

public interface EcoUserSavePort {

    EcoUser save(EcoUser ecoUser);

}
