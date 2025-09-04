package org.robn.ecommerce.product.model;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.robn.ecommerce.common.model.BaseDomainModel;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
@SuperBuilder
public class Product extends BaseDomainModel {

    private Long id;
    private UUID sellerId;
    private Long brandId;
    private String name;
    private String description;
    private BigDecimal price;
    private Boolean isActive;
    private Boolean isVerified;

    public boolean isOwnedBy(final UUID targetUserId) {
        return this.sellerId != null && this.sellerId.equals(targetUserId);
    }

}
