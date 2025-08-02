package org.robn.ecommerce.common.model.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.robn.ecommerce.user.model.entity.UserEntity;

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

    //TODO: Refactor later to implement Spring Security
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "deleted_by", insertable = false, updatable = false)
    protected UserEntity deletedBy;

}
