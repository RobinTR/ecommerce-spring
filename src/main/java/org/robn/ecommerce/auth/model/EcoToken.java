package org.robn.ecommerce.auth.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class EcoToken {

    private String accessToken;
    private String refreshToken;

}
