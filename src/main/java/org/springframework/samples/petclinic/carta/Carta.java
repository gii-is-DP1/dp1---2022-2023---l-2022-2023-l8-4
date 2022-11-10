package org.springframework.samples.petclinic.carta;

import lombok.Getter;
import lombok.Setter;
import org.springframework.samples.petclinic.model.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table ( name = "cartas" )
public class Carta extends BaseEntity {

    @Column ( name = "icons" )
    private String icons;

}
