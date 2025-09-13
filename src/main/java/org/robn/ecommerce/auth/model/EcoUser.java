package org.robn.ecommerce.auth.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.robn.ecommerce.auth.model.enums.EcoUserStatus;
import org.robn.ecommerce.common.model.BaseDomainModel;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
public class EcoUser extends BaseDomainModel {

    private UUID id;
    private String email;
    private String password;
    private EcoUserStatus ecoUserStatus;
    private List<EcoRole> roles;

}
