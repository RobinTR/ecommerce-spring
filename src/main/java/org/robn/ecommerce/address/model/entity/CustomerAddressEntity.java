package org.robn.ecommerce.address.model.entity;

import jakarta.persistence.*;
import lombok.*;
import org.robn.ecommerce.customer.model.entity.CustomerEntity;

import java.util.UUID;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "eco_customer_address")
public class CustomerAddressEntity extends AddressEntity {

    @Column(name = "customer_id")
    private UUID customerId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", insertable = false, updatable = false)
    private CustomerEntity customer;

}
