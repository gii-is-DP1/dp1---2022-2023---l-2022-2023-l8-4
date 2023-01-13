package org.springframework.samples.petclinic.playergamedata;


import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Range;
import org.springframework.samples.petclinic.card.Card;
import org.springframework.samples.petclinic.game.Game;
import org.springframework.samples.petclinic.model.BaseEntity;
import org.springframework.samples.petclinic.player.Player;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "player_game_data")
public class PlayerGameData extends BaseEntity{

	@Range(min=0,max=53)
	@Column(name = "points")
	private int pointsNumber;

	@NotNull
	private boolean winner;

	@ManyToOne
	private Card actualCard;
	
	@ManyToMany(cascade = {CascadeType.PERSIST,
			CascadeType.MERGE,
			CascadeType.DETACH,
			CascadeType.REFRESH})
	private Collection<Card> actualCards;

	@ManyToOne(cascade = CascadeType.ALL)
	private Player player;

	@ManyToOne
	private Game game;
}
