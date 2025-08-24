package org.robn.ecommerce.auth.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.robn.ecommerce.common.model.entity.SoftDeletableEntity;
import org.robn.ecommerce.auth.model.enums.EcoUserStatus;

import java.util.Set;
import java.util.UUID;

@Entity
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "eco_user")
public class EcoUserEntity extends SoftDeletableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID id;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "user_status")
    private EcoUserStatus ecoUserStatus;

    @ManyToMany
    @JoinTable(
            name = "eco_user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<EcoRoleEntity> roles;

}
