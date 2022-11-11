package org.springframework.samples.petclinic.card;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.samples.petclinic.model.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table ( name = "cards" )
public class Card extends BaseEntity {

    @Column ( name = "icons" )
    private String icons;

}
