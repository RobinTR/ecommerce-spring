package org.robn.ecommerce.cart.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.robn.ecommerce.guest.model.entity.GuestEntity;

import java.util.UUID;

@Entity
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "eco_guest_cart")
public class GuestCartEntity extends CartEntity {

    @Column(name = "guest_id")
    private UUID guestId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "guest_id", insertable = false, updatable = false)
    private GuestEntity guest;

}
