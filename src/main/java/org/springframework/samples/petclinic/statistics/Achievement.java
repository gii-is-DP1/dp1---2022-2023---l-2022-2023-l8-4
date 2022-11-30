package org.springframework.samples.petclinic.statistics;

import java.time.LocalDate;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.samples.petclinic.model.NamedEntity;
import org.springframework.samples.petclinic.player.Player;

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
    
    @ManyToMany
	@JoinTable(name = "players_achievements", joinColumns = @JoinColumn(name = "achievement_id"),
	inverseJoinColumns = @JoinColumn(name = "player_id"))
	private Collection<Player> players;
    
}