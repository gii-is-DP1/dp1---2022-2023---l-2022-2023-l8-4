package org.springframework.samples.petclinic.statistics.archivements;


import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.samples.petclinic.model.NamedEntity;
import org.springframework.samples.petclinic.player.Player;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "achievements")
public class Achievement extends NamedEntity{
	
	
	@NotEmpty
    private String description;

	@NotNull
    private double threshold;

	private double percentage;

	private AchievementTrophy trophy;

    private String badgeImage;

    public String getActualDescription(){
        return description.replace("<THRESHOLD>",String.valueOf((int)threshold));
    }

    @ManyToMany(mappedBy= "playersAchievement")
	private Collection<Player> players;

    protected Collection<Player> getPlayersInternal() {
		if (this.players == null) {
			this.players = new ArrayList<Player>();
		}
		return this.players;
	}
	
	
   
}
