package org.springframework.samples.petclinic.jugador;

import java.time.LocalDate; 
import java.util.Collection;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.samples.petclinic.model.BaseEntity;
import org.springframework.samples.petclinic.model.NamedEntity;
import org.springframework.samples.petclinic.partida.Partida;
import org.springframework.samples.petclinic.user.User;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Entity
@Table(name = "jugadores")
@ToString
@EqualsAndHashCode(callSuper=false)
public class Jugador extends BaseEntity {

	@Column(name = "fecha_registro")
	@NotNull
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate fechaRegistro;
	
	@Column(name = "fecha_modificacion")
	@NotNull
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate fechaModificacion;
	
	@Column(name = "ultimo_inicio_sesion")
	@NotNull
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate ultimoInicioSesion;
	
	@Column(name = "email")
	@NotEmpty
	@Email
	private String email;
	
	@Column(name = "fecha_nacimiento")
	@NotNull
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate fechaNacimiento;
	
	@Column(name = "foto_perfil")
	private String fotoPerfil;
	
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "username", referencedColumnName = "username")
	private User user;
	
	@ManyToMany(fetch = FetchType.EAGER,
				mappedBy="jugadores")
	private Collection<Partida> partidas_jugadas;
	
	@ManyToMany(fetch = FetchType.EAGER, mappedBy="jugadores")
	private Collection<Partida> partidasJugadas;	
}
