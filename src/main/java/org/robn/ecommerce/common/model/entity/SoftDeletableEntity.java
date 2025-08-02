package org.robn.ecommerce.common.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Getter
@SuperBuilder
@NoArgsConstructor
@MappedSuperclass
public abstract class SoftDeletableEntity extends AuditEntity {

    @Builder.Default
    @Column(name = "deleted")
    protected Boolean deleted = Boolean.FALSE;

    @Column(name = "deleted_at")
    protected LocalDateTime deletedAt;

    @Column(name = "deleted_by")
    protected String deletedBy;

}
