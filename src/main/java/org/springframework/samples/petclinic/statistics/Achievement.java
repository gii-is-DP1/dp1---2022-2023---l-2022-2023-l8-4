package org.springframework.samples.petclinic.statistics;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.samples.petclinic.model.NamedEntity;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "achievement")
public class Achievement extends NamedEntity {
	@Column(name = "description")
	@NotEmpty
    private String description;
	@Column(name = "badgeImage")
    private String badgeImage;
	@Column(name = "threshold")
	@NotNull
    private double threshold;
    
    public String getActualDescription(){
        return description.replace("<THRESHOLD>",String.valueOf(threshold));
    }
}