package org.springframework.samples.petclinic.usuario;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.samples.petclinic.model.BaseEntity;
import org.springframework.samples.petclinic.model.NamedEntity;
import org.springframework.samples.petclinic.partida.Partida;

import lombok.Data;

@Data
@Entity
public class Usuario extends NamedEntity {

	@Column(name = "contraseña")
	@NotEmpty
	private String contraseña;
	
	@Column(name = "fecha_registro")
	@NotEmpty
	@DateTimeFormat(pattern="yyyy/mm/dd'T'HH:mm:ss")
	private LocalDateTime fecha_registro;
	
	@Column(name = "fecha_modificacion")
	@NotEmpty
	@DateTimeFormat(pattern="yyyy/mm/dd'T'HH:mm:ss")
	private LocalDateTime fecha_modificacion;
	
	@Column(name = "ultimo_inicio_sesion")
	@NotEmpty
	@DateTimeFormat(pattern="yyyy/mm/dd'T'HH:mm:ss")
	private LocalDateTime ultimo_inicio_sesion;
	
	@Column(name = "nombre_usuario")
	@NotEmpty
	private String nombre_usuario;
	
	@Column(name = "email")
	@NotEmpty
	@Email
	private String email;
	
	@Column(name = "administrador")
	@NotEmpty
	private Boolean administrador;
	
	@Column(name = "fecha_nacimiento")
	@NotEmpty
	@DateTimeFormat(pattern="yyyy/mm/dd")
	private LocalDate fecha_nacimiento;
	
	@Column(name = "foto_perfil")
	@NotEmpty
	private String foto_perfil;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "creador_id")
	private Set<Partida> partidas;
}
