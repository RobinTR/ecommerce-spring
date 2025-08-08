package org.robn.ecommerce.common.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.robn.ecommerce.common.listener.AuditEntityListener;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@MappedSuperclass
@EntityListeners(AuditEntityListener.class)
public abstract class AuditEntity extends BaseEntity {

    @Column(name = "created_by", updatable = false)
    protected String createdBy;

    @Column(name = "updated_by")
    protected String updatedBy;

}
