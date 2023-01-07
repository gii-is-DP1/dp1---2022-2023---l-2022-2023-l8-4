package org.springframework.samples.petclinic.game;

import lombok.Getter;

@Getter
public enum GameMode {


	ESTANDAR(GameModeData.initEstandar()),
	EL_FOSO(GameModeData.initFoso());
	
	private final GameModeData gameModeData;
	
	GameMode(GameModeData gameModeData) {
		this.gameModeData = gameModeData;
	}
}
