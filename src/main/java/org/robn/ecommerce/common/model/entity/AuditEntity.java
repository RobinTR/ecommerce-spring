package org.robn.ecommerce.common.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
@NoArgsConstructor
@MappedSuperclass
public abstract class AuditEntity extends BaseEntity {

    @Column(name = "created_by", updatable = false)
    protected String createdBy;

    @Column(name = "updated_by")
    protected String updatedBy;

}
