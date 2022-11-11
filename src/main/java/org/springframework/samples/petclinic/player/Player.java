package org.springframework.samples.petclinic.player;

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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.samples.petclinic.game.Game;
import org.springframework.samples.petclinic.model.BaseEntity;
import org.springframework.samples.petclinic.model.NamedEntity;
import org.springframework.samples.petclinic.user.User;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Entity
@Table(name = "players")
@ToString
@EqualsAndHashCode(callSuper=false)
public class Player extends BaseEntity {

	@Column(name = "register_date")
	@NotNull
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate registerDate;
	
	@Column(name = "modification_date")
	@NotNull
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate modificationDate;
	
	@Column(name = "last_login")
	@NotNull
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate lastLogin;
	
	@Column(name = "email")
	@NotEmpty
	@Email
	private String email;
	
	@Column(name = "birth_date")
	@NotNull
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate birthDate;
	
	@Column(name = "profile_picture")
	private String profilePicture;
	
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "username", referencedColumnName = "username")
	private User user;
	
	@ManyToMany(fetch = FetchType.EAGER,
				mappedBy="players")
	private Collection<Game> playedGames;
	
}
