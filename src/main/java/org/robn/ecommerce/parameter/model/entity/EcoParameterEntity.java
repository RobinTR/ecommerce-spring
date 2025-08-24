package org.robn.ecommerce.parameter.model.entity;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.robn.ecommerce.common.model.entity.BaseEntity;

@Entity
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@Table(name = "eco_parameter")
public class EcoParameterEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "definition")
    private String definition;

}
