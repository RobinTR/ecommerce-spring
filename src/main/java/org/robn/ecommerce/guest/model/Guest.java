package org.robn.ecommerce.guest.model;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.robn.ecommerce.common.model.BaseDomainModel;

import java.util.UUID;

@Getter
@Setter
@SuperBuilder
public class Guest extends BaseDomainModel {

    private UUID id;
    private String deviceId;

}
