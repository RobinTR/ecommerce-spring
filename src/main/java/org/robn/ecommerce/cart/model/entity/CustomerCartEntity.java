package org.robn.ecommerce.cart.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.robn.ecommerce.customer.model.entity.CustomerEntity;

import java.util.UUID;

@Entity
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "eco_customer_cart")
public class CustomerCartEntity extends CartEntity {

    @Column(name = "customer_id")
    private UUID customerId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", insertable = false, updatable = false)
    private CustomerEntity customer;

}
