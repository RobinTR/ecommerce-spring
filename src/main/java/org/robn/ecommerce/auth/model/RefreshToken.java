package org.robn.ecommerce.auth.model;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.robn.ecommerce.common.model.BaseDomainModel;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@SuperBuilder
public class RefreshToken extends BaseDomainModel {

    private UUID id;
    private UUID userId;
    private String token;
    private String deviceId;
    private boolean revoked;
    private LocalDateTime expiresAt;

}
