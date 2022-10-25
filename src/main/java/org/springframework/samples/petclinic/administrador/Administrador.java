package org.springframework.samples.petclinic.administrador;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.springframework.samples.petclinic.model.Usuario;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "administrador")
public class Administrador extends Usuario{

}
