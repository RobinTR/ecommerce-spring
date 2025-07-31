package org.robn.ecommerce.seller.model.entity;

import jakarta.persistence.*;
import lombok.*;
import org.robn.ecommerce.address.model.entity.SellerAddressEntity;
import org.robn.ecommerce.seller.model.enums.SellerStatus;
import org.robn.ecommerce.user.model.entity.UserEntity;

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

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private SellerStatus status;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private SellerAddressEntity address;
}
