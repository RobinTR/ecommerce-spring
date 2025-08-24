package org.robn.ecommerce.parameter.port;

import org.robn.ecommerce.parameter.model.EcoParameter;

import java.util.List;

public interface EcoParameterReadPort {

    List<EcoParameter> findAll();

    EcoParameter findByName(String name);

}
