package org.robn.ecommerce.seller.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.robn.ecommerce.seller.model.enums.SellerStatus;
import org.robn.ecommerce.user.model.entity.UserEntity;

@Entity
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "eco_seller")
public class SellerEntity extends UserEntity {

    @Column(name = "store_name")
    private String storeName;

    @Column(name = "mersis_no")
    private String mersisNo;

    @Column(name = "contact_number")
    private String contactNumber;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private SellerStatus sellerStatus;

}
