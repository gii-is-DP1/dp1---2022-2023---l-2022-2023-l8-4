package org.springframework.samples.petclinic.jugador;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.user.AuthoritiesService;
import org.springframework.samples.petclinic.user.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class JugadorServicio {

	private JugadorRepositorio jugadorRepositorio;
	
	@Autowired
	public JugadorServicio(JugadorRepositorio jugadorRepositorio) {
		this.jugadorRepositorio = jugadorRepositorio;
	}
	@Autowired
	private UserService userService;
	
	@Autowired
	private AuthoritiesService authoritiesService;
	
	@Transactional(readOnly=true)
	public Collection<Jugador> getAllJugadores(){
		return jugadorRepositorio.findAll();
	}
	
	@Transactional(readOnly = true)
	public Jugador mostrarJugadoresPorId(Integer id) {
		Optional<Jugador> result= jugadorRepositorio.findById(id);
        return result.isPresent()?result.get():null;
		
	}
	
	@Transactional
	public void deleteJugador(Integer id) throws DataAccessException {
		jugadorRepositorio.deleteById(id);
	}
	
	@Transactional
	public void saveJugador(Jugador jugador) throws DataAccessException {
		jugador.setFechaRegistro(LocalDate.now());
    	jugador.setFechaModificacion(LocalDate.now());
    	jugador.setUltimoInicioSesion(LocalDate.now());
	
		jugadorRepositorio.save(jugador);		

		userService.saveUser(jugador.getUser());

		authoritiesService.saveAuthorities(jugador.getUser().getUsername(), "Jugador");
	}		
}
