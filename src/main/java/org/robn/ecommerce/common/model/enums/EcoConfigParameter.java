package org.robn.ecommerce.common.model.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum EcoConfigParameter {

    ECO("ECO"),

    AUTH_ACCESS_TOKEN_EXPIRE_MINUTE("180"),
    AUTH_REFRESH_TOKEN_EXPIRE_DAY("1"),
    AUTH_TOKEN_SECRET_KEY("");

    private final String defaultValue;

}
