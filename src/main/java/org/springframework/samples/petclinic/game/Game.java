package org.springframework.samples.petclinic.game;

import java.time.LocalDate; 
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
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

	@Column(name = "date")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate date;
	
	@Column(name = "game_mode")
	@NotNull
	private GameMode gameMode;
	
	@Column(name = "game_state")
	@NotNull
	private GameState gameState;
	
	@Column(name = "game_code",
			unique = true)
	private Integer gameCode;
	
	@ManyToMany
	@JoinTable(name = "players_games", joinColumns = @JoinColumn(name = "game_id"),
	inverseJoinColumns = @JoinColumn(name = "player_id"))
	private Collection<Player> players;
	
	@ManyToMany(cascade = {CascadeType.PERSIST,
					CascadeType.MERGE,
					CascadeType.DETACH,
					CascadeType.REFRESH})
	private Collection<Card> cards;
}

