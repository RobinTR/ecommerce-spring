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
@Table(name = "eco_cart_item")
public class CartItemEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID id;

    @Column(name = "cart_id")
    private UUID cartId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cart_id", insertable = false, updatable = false)
    private CartEntity cart;

    @Column(name = "product_id")
    private Long productId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", insertable = false, updatable = false)
    private ProductEntity product;

    // Snapshot fields to capture product details only when cart is successfully checked out
    @Column(name = "product_name")
    private String productName;

    @Column(name = "product_description")
    private String productDescription;

    @Column(name = "product_brand_id")
    private Long productBrandId;

    @Column(name = "product_seller_id")
    private UUID productSellerId;

    @Column(name = "product_price")
    private BigDecimal productPrice;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "discount_amount")
    private BigDecimal discountAmount;

    @Enumerated(EnumType.STRING)
    @Column(name = "cart_item_status")
    private CartItemStatus cartItemStatus;

}
