package org.springframework.samples.petclinic.card;

import lombok.Getter;
import lombok.Setter;
import org.springframework.samples.petclinic.model.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table ( name = "cards" )
public class Card extends BaseEntity {

    private String icons;

}
