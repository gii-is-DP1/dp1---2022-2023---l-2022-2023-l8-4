package org.springframework.samples.petclinic.game;

import lombok.Getter;

@Getter
public enum GameMode {

	ESTANDAR("Descripción la torre infernal ---------"),
	EL_FOSO("Descripción el foso desde-----------"),
	LA_PATATA_CALIENTE("Descripción patata caliente --------");
	
	private final String description;
	
	GameMode(String description) {
		this.description = description;
	}
}
