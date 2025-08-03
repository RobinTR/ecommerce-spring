package org.robn.ecommerce.order.model.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.robn.ecommerce.address.model.entity.GuestAddressEntity;
import org.robn.ecommerce.common.model.entity.BaseEntity;

import java.util.UUID;

@Entity
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "eco_guest_order")
public class GuestOrderEntity extends BaseOrderEntity {

    @Column(name = "session_id")
    private String sessionId;

}
