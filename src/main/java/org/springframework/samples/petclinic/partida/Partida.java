package org.springframework.samples.petclinic.partida;

import java.util.Collection;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

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
	@Temporal(TemporalType.DATE)
	private Date fecha;
	
	@Column(name = "modo")
	private Modo modo;
	
	@ManyToOne(optional=false)
	@JoinColumn(name = "creador_id")
	private Usuario creadorId;
	
	@ManyToMany
	private Collection<Usuario> jugadores;
	
	@ManyToMany(cascade = {CascadeType.PERSIST,
					CascadeType.MERGE,
					CascadeType.DETACH,
					CascadeType.REFRESH})
	private Collection<Carta> cartas;
}

