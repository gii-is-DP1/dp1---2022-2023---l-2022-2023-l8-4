package org.springframework.samples.petclinic.partida;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.samples.petclinic.carta.Carta;
import org.springframework.samples.petclinic.jugador.Jugador;
import org.springframework.samples.petclinic.model.BaseEntity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Entity
@Table(name = "partidas")
@ToString
@EqualsAndHashCode(callSuper=false)
public class Partida extends BaseEntity {

	@Column(name = "fecha")
	@NotNull
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate fecha;
	
	@Column(name = "estado")
	private Estado estado;
	
	@Column(name = "modo")
	@NotNull
	private Modo modo;
	
	@ManyToMany
	@ToString.Exclude
	@JoinTable(name = "jugadores_partidas", joinColumns = @JoinColumn(name = "partida_id"),
	inverseJoinColumns = @JoinColumn(name = "jugador_id"))
	private Collection<Jugador> jugadores;
	
	@ManyToMany(cascade = {CascadeType.PERSIST,
					CascadeType.MERGE,
					CascadeType.DETACH,
					CascadeType.REFRESH})
	private Collection<Carta> cartas;
}

