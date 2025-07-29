package org.robn.ecommerce.seller.model.entity;

import jakarta.persistence.*;
import lombok.*;
import org.robn.ecommerce.address.model.entity.AddressEntity;
import org.robn.ecommerce.seller.model.enums.SellerStatus;
import org.robn.ecommerce.user.model.entity.UserEntity;

import java.util.List;

@Entity
@Getter
@Setter
@Builder
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

    @Column(name = "address")
    private String address;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private SellerStatus status;
}
