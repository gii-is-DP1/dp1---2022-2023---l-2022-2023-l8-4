package org.springframework.samples.petclinic.statistics;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;
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
	
	@Column(name = "threshold")
	@NotNull
    private double threshold;
	
	@Column(name = "percentage")
	@NotNull
	private double percentage;
	
	@Column(name = "acquire_date")
	@DateTimeFormat(pattern = "yyyy/MM/dd")
	private LocalDate acquireDate;
	
	@Column(name = "trophy")
	private AchievementTrophy trophy;
	
	@Column(name = "badgeImage")
    private String badgeImage;
	
    public String getActualDescription(){
        return description.replace("<THRESHOLD>",String.valueOf(threshold));
    }
    
}