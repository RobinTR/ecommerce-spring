package org.robn.ecommerce.seller.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.robn.ecommerce.auth.model.entity.EcoUserEntity;
import org.robn.ecommerce.seller.model.enums.SellerStatus;
import org.robn.ecommerce.seller.model.enums.SellerType;

@Entity
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "eco_seller")
public class SellerEntity extends EcoUserEntity {

    @Column(name = "store_name")
    private String storeName;

    @Column(name = "mersis_number")
    private String mersisNumber;

    @Column(name = "contact_number")
    private String contactNumber;

    @Enumerated(EnumType.STRING)
    @Column(name = "seller_type")
    private SellerType sellerType;

    @Enumerated(EnumType.STRING)
    @Column(name = "seller_status")
    private SellerStatus sellerStatus;

}
