package org.springframework.samples.petclinic.carta;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.samples.petclinic.model.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@Table(name = "cartas")
public class Carta extends BaseEntity {
    @Column(name = "icons")
    private String icons;

}
