package org.robn.ecommerce.product.model;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.robn.ecommerce.common.model.BaseDomainModel;

import java.util.UUID;

@Getter
@Setter
@SuperBuilder
public class ProductImage extends BaseDomainModel {

    private UUID id;
    private Long productId;
    private String publicId;
    private String imageUrl;
    private String format;
    private Integer width;
    private Integer height;
    private Long sizeBytes;
    private String altText;

}
