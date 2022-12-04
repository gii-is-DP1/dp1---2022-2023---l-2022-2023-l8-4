package org.springframework.samples.petclinic.game;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.ThreadLocalRandom;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;

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
	@NotNull
	@PositiveOrZero
	private Integer gameCode;
	
	@ManyToMany(mappedBy= "playedGames")
	@Size(max = 4)
	private Collection<Player> players;
	
	@ManyToMany(cascade = {CascadeType.PERSIST,
					CascadeType.MERGE,
					CascadeType.DETACH,
					CascadeType.REFRESH})
	private Collection<Card> cards;
	
	public Game() {
		this.date = LocalDate.now();
		this.gameState = GameState.INITIATED;
		this.gameCode = ThreadLocalRandom.current().nextInt(0, 10000 + 1);
	}
	
	
	protected Collection<Player> getPlayersInternal() {
		if (this.players == null) {
			this.players = new ArrayList<Player>();
		}
		return this.players;
	}
	
	public void addPlayer(Player player) {
		getPlayersInternal().add(player);
		player.addGame(this);
	}
	
	public boolean removePlayer(Player player) {
		return getPlayersInternal().remove(player);
	}
}