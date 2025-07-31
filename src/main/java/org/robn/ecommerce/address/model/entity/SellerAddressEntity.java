package org.robn.ecommerce.address.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.robn.ecommerce.seller.model.entity.SellerEntity;

import java.util.UUID;

@Entity
@Getter
@Setter
@SuperBuilder
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
