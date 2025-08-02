package org.robn.ecommerce.user.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.robn.ecommerce.common.model.BaseDomainModel;
import org.robn.ecommerce.user.model.enums.UserStatus;

import java.util.UUID;

@Getter
@Setter
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
public class EcoUser extends BaseDomainModel {

    private UUID id;
    private String email;
    private String password;
    private UserStatus status;

}
