package org.springframework.samples.petclinic.usuario;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.springframework.samples.petclinic.model.NamedEntity;
import org.springframework.samples.petclinic.partida.Partida;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Entity
@Table(name = "usuarios")
@ToString
@EqualsAndHashCode(callSuper=false)
public class Usuario extends NamedEntity {

	@Column(name = "contrasena", nullable = false)
	@NotEmpty
	private String contraseña;
	
	@Column(name = "fecha_registro")
	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaRegistro;
	
	@Column(name = "fecha_modificacion")
	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaModificacion;
	
	@Column(name = "ultimo_inicio_sesion")
	@Temporal(TemporalType.TIMESTAMP)
	private Date ultimoInicioSesion;
	
	@Column(name = "nombre_usuario", nullable = false)
	@NotEmpty
	private String nombreUsuario;
	
	@Column(name = "email")
	@NotEmpty
	@Email
	private String email;
	
	@Column(name = "es_administrador", nullable = false)
	private boolean esAdministrador;
	
	@Column(name = "fecha_nacimiento")
	@Temporal(TemporalType.DATE)
	private Date fechaNacimiento;
	
	@Column(name = "foto_perfil")
	@NotEmpty
	private String fotoPerfil;
	
	@OneToMany(mappedBy = "creadorPartida")
	private Collection<Partida> partidasCreadas;
	
	@ManyToMany(fetch = FetchType.EAGER, mappedBy="jugadores")
	private Collection<Partida> partidasJugadas;	
}
