package org.springframework.samples.petclinic.game;

import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.text.ParseException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

public class GameModeFormatterTest {

	private GameModeFormatter gameModeFormatter;
	
	@BeforeEach
	void setup() {
		this.gameModeFormatter = new GameModeFormatter();
	}
	
	@Test
	void shouldPrintGameMode() {
		String estandarName = "ESTANDAR";
		String elFosoName = "EL FOSO";
		GameMode estandar = GameMode.ESTANDAR;
		GameMode elFoso = GameMode.EL_FOSO;

		
		String actualEstandar = gameModeFormatter.print(estandar, null);
		String actualFoso = gameModeFormatter.print(elFoso, null);

		assertEquals(estandarName, actualEstandar, "Estandar name does not match");
		assertEquals(elFosoName, actualFoso, "El FOSO mode does not match");

	}
	
	@ParameterizedTest
	@CsvFileSource(resources = "/GameModeFormatterPositiveValues.csv", delimiter=';')
	void shouldParseGameMode(String gameMode, String value) {
		
		try {
			GameMode actualEstandar = gameModeFormatter.parse(value, null);
			assertEquals(GameMode.valueOf(gameMode), actualEstandar, "Estandar name does not match");
			
		} catch (ParseException e) {
			fail();
		}
	}
	
	@ParameterizedTest
	@CsvFileSource(resources = "/GameModeFormatterNegativeValues.csv", delimiter=';')
	void shouldThrowParseException(String gameMode, String value) {
		
			assertThrows(ParseException.class, () -> {gameModeFormatter.parse(value, null);});
			
	}
	
}
