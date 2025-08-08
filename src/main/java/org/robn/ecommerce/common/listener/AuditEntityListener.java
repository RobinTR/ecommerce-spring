package org.robn.ecommerce.common.listener;

import jakarta.persistence.PrePersist;
import org.robn.ecommerce.common.model.entity.AuditEntity;

public class AuditEntityListener {

    // TODO: Refactor for Spring Security
    // This listener is used to set default values for createdBy and updatedBy fields
    // in the AuditEntity before persisting it to the database.
    @PrePersist
    public void prePersist(AuditEntity auditEntity) {
        if (auditEntity.getCreatedBy() == null) {
            auditEntity.setCreatedBy("eco");
        }

        if (auditEntity.getUpdatedBy() == null) {
            auditEntity.setUpdatedBy("eco");
        }
    }

}
