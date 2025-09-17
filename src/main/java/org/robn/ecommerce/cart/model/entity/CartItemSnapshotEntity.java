package org.robn.ecommerce.cart.model.entity;

import jakarta.persistence.*;
import lombok.*;
import org.robn.ecommerce.cart.model.enums.CartItemStatus;
import org.robn.ecommerce.common.model.entity.BaseEntity;
import org.robn.ecommerce.product.model.entity.ProductEntity;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "eco_cart_item_snapshot")
public class CartItemSnapshotEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID id;

    @Column(name = "cart_snapshot_id")
    private UUID cartSnapshotId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cart_snapshot_id", insertable = false, updatable = false)
    private CartSnapshotEntity cartSnapshot;

    @Column(name = "cart_item_id")
    private UUID cartItemId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cart_item_id", insertable = false, updatable = false)
    private CartItemEntity cartItem;

    @Column(name = "product_id")
    private Long productId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", insertable = false, updatable = false)
    private ProductEntity product;

    @Column(name = "product_seller_id", updatable = false)
    private UUID productSellerId;

    @Column(name = "product_brand_id", updatable = false)
    private Long productBrandId;

    @Column(name = "product_name", updatable = false)
    private String productName;

    @Column(name = "product_description", updatable = false)
    private String productDescription;

    @Column(name = "product_price", updatable = false)
    private BigDecimal productPrice;

    @Column(name = "discount_amount", updatable = false)
    private BigDecimal discountAmount;

    @Column(name = "quantity", updatable = false)
    private Integer quantity;

    @Enumerated(EnumType.STRING)
    @Column(name = "cart_item_status")
    private CartItemStatus cartItemStatus;

}
