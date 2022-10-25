package org.springframework.samples.petclinic.jugador;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.samples.petclinic.model.Usuario;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "jugador")
public class Jugador extends Usuario{
	
	@NotEmpty
	@DateTimeFormat(pattern = "yyyy/mm/dd")
	private LocalDate lastLogIN;
	
	@NotEmpty
	private String loginPicture;

}
