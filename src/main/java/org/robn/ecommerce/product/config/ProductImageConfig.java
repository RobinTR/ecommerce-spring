package org.robn.ecommerce.product.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.util.unit.DataSize;

import java.util.List;

@ConfigurationProperties(prefix = "product.image")
@Getter
@Setter
@Component
public class ProductImageConfig {

    private Integer minCount;
    private Integer maxCount;
    private DataSize maxSize;
    private List<String> allowedTypes;

}
