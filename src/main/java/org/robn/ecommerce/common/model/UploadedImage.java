package org.robn.ecommerce.common.model;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
public class UploadedImage extends BaseDomainModel {

    private String publicId;
    private String url;
    private String format;
    private Integer width;
    private Integer height;
    private Long sizeBytes;

}
