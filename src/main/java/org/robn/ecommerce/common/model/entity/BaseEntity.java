package org.robn.ecommerce.common.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
import java.util.Optional;

@Getter
@SuperBuilder
@NoArgsConstructor
@MappedSuperclass
public abstract class BaseEntity {

    @Column(name = "created_at", updatable = false)
    protected LocalDateTime createdAt;

    @Column(name = "updated_at")
    protected LocalDateTime updatedAt;

    @Column(name = "created_by", updatable = false)
    protected String createdBy;

    @Column(name = "updated_by")
    protected String updatedBy;


    /*
     * TODO: Refactor for Spring Security - Jwt
     */
    @PrePersist
    public void prePersist() {

        this.createdAt = Optional.ofNullable(this.createdAt)
                .orElse(LocalDateTime.now());

        this.createdBy = Optional.ofNullable(this.createdBy)
                .orElse("eco");

    }

    @PreUpdate
    public void preUpdate() {

        this.updatedAt = LocalDateTime.now();
        this.updatedBy = "eco";

    }

}
