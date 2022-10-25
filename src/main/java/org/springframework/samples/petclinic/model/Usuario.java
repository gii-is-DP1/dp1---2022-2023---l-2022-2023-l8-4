package org.springframework.samples.petclinic.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
@MappedSuperclass
public class Usuario extends BaseEntity{
	@Column(name = "name")
	@NotEmpty
	private String name;
	@Column(name = "username")
	@NotEmpty
	private String username;
	@Column(name = "password")
	@NotEmpty
	private String password;
	@Column(name = "email")
	@NotEmpty
	@Size(min = 3, max = 50)
	private String email;
	@Column(name = "registerDate")
	@NotEmpty
	@DateTimeFormat(pattern = "yyyy/mm/dd")
	private LocalDate registerDate;
	@Column(name = "lastModication")
	@NotEmpty
	@DateTimeFormat(pattern = "yyyy/mm/dd")
	private LocalDate lastModication;
	@Column(name = "lastLogIN")
	@NotEmpty
	@DateTimeFormat(pattern = "yyyy/mm/dd")
	private LocalDate lastLogIN;
	

}
