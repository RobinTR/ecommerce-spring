package org.robn.ecommerce.auth.config;

import lombok.Getter;
import org.robn.ecommerce.common.model.enums.EcoConfigParameter;
import org.robn.ecommerce.parameter.model.EcoParameter;
import org.robn.ecommerce.parameter.port.EcoParameterReadPort;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.Optional;

@Getter
@Configuration
public class EcoAuthConfiguration {

    private final String tokenIssuer;
    private final Integer accessTokenExpireMinute;
    private final Integer refreshTokenExpireDay;
    private final String secretKey;

    public EcoAuthConfiguration(final EcoParameterReadPort parameterReadPort, @Value("${security.jwt.secret-key}") final String secretKey) {
        final List<EcoParameter> parameters = List.copyOf(parameterReadPort.findAll());
        this.secretKey = secretKey;
        this.tokenIssuer = EcoConfigParameter.ECO.getDefaultValue();
        this.accessTokenExpireMinute = getIntParameter(parameters, EcoConfigParameter.AUTH_ACCESS_TOKEN_EXPIRE_MINUTE);
        this.refreshTokenExpireDay = getIntParameter(parameters, EcoConfigParameter.AUTH_REFRESH_TOKEN_EXPIRE_DAY);
    }

    private Integer getIntParameter(final List<EcoParameter> parameters, final EcoConfigParameter config) {
        return Optional.ofNullable(EcoParameter.getDefinition(config, parameters))
                .map(Integer::parseInt)
                .orElse(Integer.parseInt(config.getDefaultValue()));
    }

}
