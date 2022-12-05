package org.springframework.samples.petclinic.game;

import lombok.Getter;

@Getter
public enum GameMode {

	ESTANDAR("Descripción estandar"),
	EL_FOSO("Descripción el foso"),
	LA_PATATA_CALIENTE("Descripción patata caliente");
	
	private final String description;
	
	GameMode(String description) {
		this.description = description;
	}
}
