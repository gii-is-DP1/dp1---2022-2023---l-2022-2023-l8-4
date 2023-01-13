package org.springframework.samples.petclinic.game;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public final class GameModeData {

	private final String setup;
	
	private final String objective;
	
	private final String howToPlay;
	
	private final String ending;
	
	private final boolean hasWinner;
	

	public static GameModeData initEstandar() {
		String setup = "Shuffle the cards and deal one face down to each player." 
				+ " Form a tower in the center of the table with the rest of the cards face up.";
		String objective = "Be the player who gets the most cards from the tower when the game is over.";
		String howToPlay = "All players turn their card face up at the same time." + 
		" You have to be the fastest to find the identical symbol between your card and the first card of the tower."
		+ "The first player to do so will take the card from the tower and place it on their card. By doing this, there will be a new reference card in the tower."
		+ "The game continues until all the cards in the tower have been recovered.";
		
		String ending = "The game continues until all the cards in the tower have been recovered.";
		
		return new GameModeData(setup, objective, howToPlay, ending, true);
	}
	
	public static GameModeData initFoso() {
		String setup = "Deal all the cards one by one to all the players."
				+ " Place the last card face up in the center of the table (el foso). Each player will shuffle their cards and form a deck that they will place in front of them face down.";
		String objective = "Be the fastest to get rid of all the cards and, above all, don't be the last!";
		String howToPlay = "All players place their deck face up at the same time. You have to be faster than the others to get rid of the cards in your deck by putting them on the center card."
		+" For this, you only have to identify the identical symbol between the first card of your deck and the card in the center. You have to be very fast, since the card in the center changes every time a player puts one of his cards on top.";
		String ending = "The first one to get rid of all their cards will win the mini-game.";
		return new GameModeData(setup, objective, howToPlay, ending, false);
	}
	
}
