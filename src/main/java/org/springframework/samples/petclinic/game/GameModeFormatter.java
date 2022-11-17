package org.springframework.samples.petclinic.game;

import java.text.ParseException;
import java.util.Locale;

import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

@Component
public class GameModeFormatter implements Formatter<GameMode> {

	@Override
	public String print(GameMode gameMode, Locale locale) {
		String gameModeName = gameMode.toString().replace("_", " ").toUpperCase();
		return gameModeName;
	}

	@Override
	public GameMode parse(String text, Locale locale) throws ParseException {
		GameMode[] gameModes = GameMode.values();
		text = text.trim().replace(" ", "_").toUpperCase();
		for (GameMode gameMode : gameModes) {
			if (gameMode.toString().equals(text)) {
				return gameMode;
			}
		}
		throw new ParseException("Game mode not found: " + text, 0);
	}

}
