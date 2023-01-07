package org.springframework.samples.petclinic.game;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public final class GameModeData {

	private String setup;
	
	private String objective;
	
	private String howToPlay;
	
	private String ending;
	
	private boolean hasWinner;
	

	public static GameModeData initEstandar() {
		String setup = "Baraja las cartas y reparte una boca abajo a cada jugador." 
				+ "Forma una torre en el centro de la mesa con el resto de cartas boca arriba.";
		String objective = "Ser el jugador que consiga más cartas de la torre cuando se termine el juego.";
		String howToPlay = "Todos los jugadores ponen su carta boca arriba a la vez." + 
		" Tienes que ser el más rápido en encontrar el símbolo idéntico entre tu carta y la primera carta de la torre."
		+ "El primer jugador que lo consiga, nombrará el símbolo en voz alta, cogerá la carta de la torre y la pondrá sobre su carta. Al hacer ésto, habrá una nueva carta de referencia en la torre."
		+ " La partida continúa hasta que todas las cartas de la torre hayan sido recuperadas.";
		
		String ending = "El juego terminará cuando se hayan recuperado todas las cartas de la torre. El ganador será el jugador que haya conseguido más cartas.";
		
		return new GameModeData(setup, objective, howToPlay, ending, true);
	}
	
	public static GameModeData initFoso() {
		String setup = "Reparte todas las cartas una a una entre todos los jugadores, empezando por el ganador del juego anterior."
				+ "Deja la última carta boca arriba en el centro de la mesa (el “foso”). Cada jugador mezclará sus cartas y formará un mazo que pondrá frente a él boca abajo";
		String objective = "Ser el más rápido en deshacerse de todas las cartas y, sobre todo, ¡no ser el último!";
		String howToPlay = "Todos los jugadores ponen su mazo boca arriba a la vez. Has de ser más rápido que los demás para deshacerte de las cartas de tu mazo poniéndolas sobre la carta del centro."
		+" Para ésto, sólo tienes que decir en voz alta el símbolo idéntico entre la primera carta de tu mazo y la carta del centro. Hay que ser muy rápido, ya que la carta del centro cambia cada vez que un jugador pone una de sus cartas encima.";
		String ending = "El último en deshacerse de todas sus cartas perderá el mini-juego.";
		return new GameModeData(setup, objective, howToPlay, ending, false);
	}
	
}
