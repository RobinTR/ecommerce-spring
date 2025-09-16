package org.robn.ecommerce.payment.model.entity;

import jakarta.persistence.*;
import lombok.*;
import org.robn.ecommerce.common.model.entity.BaseEntity;
import org.robn.ecommerce.order.model.entity.OrderEntity;
import org.robn.ecommerce.payment.model.enums.PaymentMethod;
import org.robn.ecommerce.payment.model.enums.PaymentProvider;
import org.robn.ecommerce.payment.model.enums.PaymentStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "eco_payment")
public class PaymentEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID id;

    @Column(name = "order_id")
    private UUID orderId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", insertable = false, updatable = false)
    private OrderEntity order;

    @Enumerated(EnumType.STRING)
    @Column(name = "payment_method")
    private PaymentMethod paymentMethod;

    @Enumerated(EnumType.STRING)
    @Column(name = "payment_provider")
    private PaymentProvider paymentProvider;

    @Column(name = "transaction_id")
    // This is the ID returned by the payment gateway
    private String transactionId;

    @Column(name = "amount")
    private BigDecimal amount;

    @Column(name = "currency")
    private String currency;

    @Column(name = "paid_at")
    private LocalDateTime paidAt;

    @Column(name = "error_message")
    private String errorMessage;

    @Enumerated(EnumType.STRING)
    @Column(name = "payment_status")
    private PaymentStatus paymentStatus;

}
