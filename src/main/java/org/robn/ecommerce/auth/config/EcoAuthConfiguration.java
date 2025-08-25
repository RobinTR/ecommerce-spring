package org.robn.ecommerce.auth.config;

import lombok.Getter;
import org.robn.ecommerce.common.model.enums.EcoConfigParameter;
import org.robn.ecommerce.parameter.model.EcoParameter;
import org.robn.ecommerce.parameter.port.EcoParameterReadPort;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.Optional;

@Getter
@Configuration
public class EcoAuthConfiguration {

    private final String tokkenIssuer;

    private final String tokenSecretKey;

    private final Integer accessTokenExpireMinute;

    private final Integer refreshTokenExpireDay;

    public EcoAuthConfiguration(EcoParameterReadPort parameterReadPort) {
        final List<EcoParameter> parameters = parameterReadPort.findAll();

        this.tokkenIssuer = EcoConfigParameter.ECO.getDefaultValue();

        final String token = Optional.ofNullable(EcoParameter.getDefinition(EcoConfigParameter.AUTH_TOKEN_SECRET_KEY, parameters))
                .orElse(EcoConfigParameter.AUTH_TOKEN_SECRET_KEY.getDefaultValue());
        this.tokenSecretKey = token;

        this.accessTokenExpireMinute = Optional.ofNullable(EcoParameter.getDefinition(EcoConfigParameter.AUTH_ACCESS_TOKEN_EXPIRE_MINUTE, parameters))
                .map(Integer::valueOf)
                .orElse(Integer.valueOf(EcoConfigParameter.AUTH_ACCESS_TOKEN_EXPIRE_MINUTE.getDefaultValue()));

        this.refreshTokenExpireDay = Optional.ofNullable(EcoParameter.getDefinition(EcoConfigParameter.AUTH_REFRESH_TOKEN_EXPIRE_DAY, parameters))
                .map(Integer::valueOf)
                .orElse(Integer.valueOf(EcoConfigParameter.AUTH_REFRESH_TOKEN_EXPIRE_DAY.getDefaultValue()));

    }

}
