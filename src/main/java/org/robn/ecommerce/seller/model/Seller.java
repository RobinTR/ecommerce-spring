package org.robn.ecommerce.seller.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.robn.ecommerce.auth.model.EcoUser;
import org.robn.ecommerce.seller.model.enums.SellerStatus;
import org.robn.ecommerce.seller.model.enums.SellerType;

@Getter
@Setter
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
public class Seller extends EcoUser {

    private String storeName;
    private String mersisNumber;
    private String contactNumber;
    private SellerType sellerType;
    private SellerStatus sellerStatus;

}
