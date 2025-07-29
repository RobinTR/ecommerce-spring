package org.robn.ecommerce.address.model.entity;

import jakarta.persistence.*;
import lombok.*;
import org.robn.ecommerce.common.model.entity.BaseEntity;
import org.robn.ecommerce.customer.model.entity.CustomerEntity;

import java.util.UUID;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "eco_address", indexes = {
        @Index(name = "idx_address_city", columnList = "city"),
        @Index(name = "idx_address_city_district", columnList = "city, district"),
        @Index(name = "idx_address_city_district_neighborhood", columnList = "city, district, neighborhood")
})
public class AddressEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID id;

    @Column(name = "title")
    private String title;

    @Column(name = "city")
    private String city;

    @Column(name = "district")
    private String district;

    @Column(name = "neighborhood")
    private String neighborhood;

    @Column(name = "address", length = 500)
    private String address;

    @Column(name = "is_default")
    private Boolean isDefault;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", insertable = false, updatable = false)
    private CustomerEntity customer;
}
