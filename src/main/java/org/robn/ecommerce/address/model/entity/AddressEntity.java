package org.robn.ecommerce.address.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.robn.ecommerce.common.model.entity.AuditEntity;

import java.util.UUID;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "eco_address", indexes = {
        @Index(name = "idx_address_city", columnList = "city"),
        @Index(name = "idx_address_city_district", columnList = "city, district"),
        @Index(name = "idx_address_city_district_neighborhood", columnList = "city, district, neighborhood")
})
public class AddressEntity extends AuditEntity {

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

}
