
package org.springframework.samples.petclinic.usuario;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;
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
public class Usuario extends NamedEntity {

	@Column(name = "contrasena")
	@NotEmpty
	private String contrasena;
	
	@Column(name = "fecha_registro")
	@NotNull
	@DateTimeFormat(pattern = "yyyy/MM/dd")
	private LocalDate fechaRegistro;
	
	@Column(name = "fecha_modificacion")
	@NotNull
	@DateTimeFormat(pattern = "yyyy/MM/dd")
	private LocalDate fechaModificacion;
	
	@Column(name = "ultimo_inicio_sesion")
	@NotNull
	@DateTimeFormat(pattern = "yyyy/MM/dd")
	private LocalDate ultimoInicioSesion;
	
	@Column(name = "nombre_usuario")
	@NotEmpty
	private String nombreUsuario;
	
	@Column(name = "email")
	@NotEmpty
	@Email
	private String email;
	
	@Column(name = "administrador")
	@NotNull
	private Boolean administrador;
	
	@Column(name = "fechaNacimiento")
	@DateTimeFormat(pattern = "yyyy/MM/dd")
	private LocalDate fechaNacimiento;
	
	@Column(name = "foto_perfil")
	private String fotoPerfil;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "creador_id")
	private Collection<Partida> partidas_creadas;
	
	@ManyToMany(fetch = FetchType.EAGER,
				mappedBy="jugadores")
	private Collection<Partida> partidas_jugadas;
	
}
