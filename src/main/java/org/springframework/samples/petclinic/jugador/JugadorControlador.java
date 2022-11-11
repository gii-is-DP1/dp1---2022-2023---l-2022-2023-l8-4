package org.springframework.samples.petclinic.jugador;


import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/jugadores")
public class JugadorControlador {
	
	private JugadorServicio jugadorServicio;
	public static final String jugador_LISTADO = "jugadores/jugadorListado";
    public static final String jugador_EDICION = "jugadores/editarJugador";
	
	@Autowired
	public JugadorControlador(JugadorServicio jugadorServicio) {
		this.jugadorServicio = jugadorServicio;
	}
	
	@GetMapping("")
    public ModelAndView mostrarTodosLosjugadores() {
        ModelAndView result = new ModelAndView(jugador_LISTADO);
        result.addObject("jugadores", jugadorServicio.getAllJugadores());
        return result;
	}
	
	@GetMapping("/delete/{id}")
    public ModelAndView eliminarjugadoresPorId(@PathVariable("id") Integer id) {
        jugadorServicio.deleteJugador(id);
        return mostrarTodosLosjugadores();
    }
	
	@GetMapping("/edit/{id}")
    public ModelAndView editJugador(@PathVariable("id") Integer id) {
        ModelAndView result = new ModelAndView(jugador_EDICION);
        result.addObject("jugadores", jugadorServicio.mostrarJugadoresPorId(id));
        return result;
    }
	
    @PostMapping("/edit/{id}")
    public ModelAndView editJugador(@PathVariable("id") Integer id, @Valid Jugador jugador2,BindingResult br) {        
        ModelAndView result=null;
        if(br.hasErrors()) {
            result = new ModelAndView(jugador_EDICION);
            result.addAllObjects(br.getModel());
        }else {
            Jugador jugador = jugadorServicio.mostrarJugadoresPorId(id);
            if(jugador !=null) {
                BeanUtils.copyProperties(jugador, jugador2,"id");
                
                jugadorServicio.saveJugador(jugador2);
                result = mostrarTodosLosjugadores();
                result.addObject("message", "Jugador editado satisfactoriamente");
                result = new ModelAndView("Bienvenido");
               
            }else {
                result = mostrarTodosLosjugadores();
                result.addObject("message", "Jugador con id "+id+" no ha sido editado correctamente");
            }
           
            
        }
        return result;
        
    }
    
    @GetMapping("/create")
    public ModelAndView createJugador() {
        ModelAndView result = new ModelAndView(jugador_EDICION);
        Jugador jugador = new Jugador();
        result.addObject("jugador", jugador);
        return result;
        
    }
    
    
    @PostMapping("/create")
    public ModelAndView saveNewJugador(@Valid Jugador jugador,BindingResult br) {
        ModelAndView result=null;
        if(br.hasErrors()) {
            result = new ModelAndView(jugador_EDICION);
            result.addAllObjects(br.getModel());
        }else {
            jugadorServicio.saveJugador(jugador);
            result = mostrarTodosLosjugadores();
            result.addObject("message", "Jugador creado satisfactoriamente");
            result = new ModelAndView("Bienvenido");
            
        }
        return result;
    }

}