package org.robn.ecommerce.brand.model.entity;

import jakarta.persistence.*;
import lombok.*;
import org.robn.ecommerce.common.model.entity.BaseEntity;
import org.robn.ecommerce.product.model.entity.ProductEntity;

import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "eco_brand")
public class BrandEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "brand", fetch = FetchType.LAZY)
    private List<ProductEntity> products;
}
