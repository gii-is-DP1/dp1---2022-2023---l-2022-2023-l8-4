package org.springframework.samples.petclinic.partida;

import java.util.Collection;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;

import org.springframework.samples.petclinic.carta.Carta;
import org.springframework.samples.petclinic.model.BaseEntity;
import org.springframework.samples.petclinic.usuario.Usuario;

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
	@NotEmpty
	@Temporal(TemporalType.DATE)
	private Date fecha;
	
	@Column(name = "modo")
	@NotEmpty
	private Modo modo;
	
	@ManyToOne(optional=false)
	@NotEmpty
	@JoinColumn(name = "creador_id")
	private Usuario creador_id;
	
	@ManyToMany
	@NotEmpty
	@JoinTable(name = "jugadores_partidas", joinColumns = @JoinColumn(name = "partida_id"),
	inverseJoinColumns = @JoinColumn(name = "jugador_id"))
	private Collection<Usuario> jugadores;
	
	@ManyToMany(cascade = {CascadeType.PERSIST,
					CascadeType.MERGE,
					CascadeType.DETACH,
					CascadeType.REFRESH})
	private Collection<Carta> cartas;
}

