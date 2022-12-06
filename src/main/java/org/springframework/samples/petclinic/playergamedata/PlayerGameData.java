package org.springframework.samples.petclinic.playergamedata;


import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
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
	@Column(name = "points_number")
	private int pointsNumber;
	
	@NotNull
	@Column(name = "winner")
	private boolean winner;
	
	@ManyToOne
	private Card actualCard;
	
	@ManyToOne(cascade = CascadeType.ALL)
	private Player player;
	
	@ManyToOne
	private Game game;
}
