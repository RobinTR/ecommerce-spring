package org.robn.ecommerce.address.model.entity;

import jakarta.persistence.*;
import lombok.*;
import org.robn.ecommerce.customer.model.entity.CustomerEntity;
import org.robn.ecommerce.seller.model.entity.SellerEntity;

import java.util.UUID;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "eco_seller_address")
public class SellerAddressEntity extends AddressEntity {

    @Column(name = "seller_id")
    private UUID sellerId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "seller_id", insertable = false, updatable = false)
    private SellerEntity seller;

    @Column(name = "tax_office")
    private String taxOffice;

}
