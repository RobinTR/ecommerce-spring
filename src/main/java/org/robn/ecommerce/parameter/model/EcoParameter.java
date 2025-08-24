package org.robn.ecommerce.parameter.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.robn.ecommerce.common.model.enums.EcoConfigParameter;

import java.util.Collection;

@Getter
@Setter
@NoArgsConstructor
public class EcoParameter {

    private String name;
    private String definition;

    public static String getDefinition(final EcoConfigParameter configParameter, final Collection<EcoParameter> parameters) {
        return parameters.stream()
                .filter(p -> p.getName().equals(configParameter.name()))
                .findFirst()
                .map(EcoParameter::getDefinition)
                .orElse(null);
    }

}
