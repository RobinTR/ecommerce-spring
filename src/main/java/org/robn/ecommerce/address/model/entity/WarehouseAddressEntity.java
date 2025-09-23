package org.robn.ecommerce.address.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.robn.ecommerce.warehouse.model.entity.WarehouseEntity;

import java.util.UUID;

@Entity
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "eco_warehouse_address")
public class WarehouseAddressEntity extends AddressEntity {

    @Column(name = "warehouse_id")
    private UUID warehouseId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "warehouse_id", insertable = false, updatable = false)
    private WarehouseEntity warehouse;

}
