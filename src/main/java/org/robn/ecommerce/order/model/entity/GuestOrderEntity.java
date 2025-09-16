package org.robn.ecommerce.order.model.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.robn.ecommerce.guest.model.entity.GuestEntity;

import java.util.UUID;

@Entity
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "eco_guest_order")
public class GuestOrderEntity extends OrderEntity {

    @Column(name = "guest_id")
    private UUID guestId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "guest_id", insertable = false, updatable = false)
    private GuestEntity guest;

}
