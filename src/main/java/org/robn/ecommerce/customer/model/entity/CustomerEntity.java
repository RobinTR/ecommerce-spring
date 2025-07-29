package org.robn.ecommerce.customer.model.entity;

import jakarta.persistence.*;
import lombok.*;
import org.robn.ecommerce.address.model.entity.AddressEntity;
import org.robn.ecommerce.user.model.entity.UserEntity;

import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "eco_customer")
public class CustomerEntity extends UserEntity {
    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "phone_number")
    private String phoneNumber;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<AddressEntity> addresses;
}
