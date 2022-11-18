package org.springframework.samples.petclinic.game;

import java.time.LocalDate; 
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.samples.petclinic.card.Card;
import org.springframework.samples.petclinic.model.BaseEntity;
import org.springframework.samples.petclinic.player.Player;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Entity
@Table(name = "games")
@ToString
@EqualsAndHashCode(callSuper=false)
public class Game extends BaseEntity {

	@Column(name = "date", nullable = false)
	@NotNull
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate date;
	
	@Column(name = "game_mode", nullable = false)
	@NotNull
	private GameMode gameMode;
	
	@Column(name = "game_state", nullable = false)
	@NotNull
	private GameState gameState;
	
	@Column(name = "game_code", unique = true, nullable = false)
	private Integer gameCode;
	
	@ManyToMany(mappedBy= "playedGames")
	private Collection<Player> players;
	
	@ManyToMany(cascade = {CascadeType.PERSIST,
					CascadeType.MERGE,
					CascadeType.DETACH,
					CascadeType.REFRESH})
	private Collection<Card> cards;
}