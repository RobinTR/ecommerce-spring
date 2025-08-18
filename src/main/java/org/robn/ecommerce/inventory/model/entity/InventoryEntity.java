package org.robn.ecommerce.inventory.model.entity;

import jakarta.persistence.*;
import lombok.*;
import org.robn.ecommerce.common.model.entity.BaseEntity;
import org.robn.ecommerce.product.model.entity.ProductEntity;
import org.robn.ecommerce.warehouse.model.entity.WarehouseEntity;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "eco_inventory")
public class InventoryEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "product_id")
    private Long productId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", insertable = false, updatable = false)
    private ProductEntity product;

    @Column(name = "warehouse_id")
    private Long warehouseId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "warehouse_id", insertable = false, updatable = false)
    private WarehouseEntity warehouse;

    @Column(name = "stock_quantity")
    private Integer stockQuantity;

}
