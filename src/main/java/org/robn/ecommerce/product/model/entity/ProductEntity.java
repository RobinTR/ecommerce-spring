package org.robn.ecommerce.product.model.entity;

import jakarta.persistence.*;
import lombok.*;
import org.robn.ecommerce.brand.model.entity.BrandEntity;
import org.robn.ecommerce.common.model.entity.BaseEntity;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "eco_product")
public class ProductEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID id;

    @Column(name = "category_id")
    private Long categoryId;

    @Column(name = "brand_id")
    private Long brandId;

    @ManyToOne
    @JoinColumn(name = "brand_id", updatable = false, insertable = false)
    private BrandEntity brand;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "stock_quantity")
    private Integer stockQuantity;

    @Column(name = "is_active")
    private Boolean isActive;

    @Column(name = "is_verified")
    private Boolean isVerified;
}
