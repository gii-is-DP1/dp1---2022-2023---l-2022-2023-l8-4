package org.springframework.samples.petclinic.usuario;

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
@RequestMapping("/usuarios")
public class UsuarioControlador {
	
	private UsuarioServicio usuarioServicio;
	public static final String USUARIO_LISTADO = "usuarios/UsuarioListado";
    public static final String USUARIO_EDICION = "usuarios/EditarUsuario";
	
	@Autowired
	public UsuarioControlador(UsuarioServicio usuarioServicio) {
		this.usuarioServicio = usuarioServicio;
	}
	
	@GetMapping("/")
    public ModelAndView mostralTodosLosUsuarios() {
        ModelAndView result = new ModelAndView(USUARIO_LISTADO);
        result.addObject("usuarios", usuarioServicio.getAllUsuarios());
        return result;
	}
	
	@GetMapping("/jugadores")
    public ModelAndView mostralTodosLosUsuariosJugadores() {
        ModelAndView result = new ModelAndView(USUARIO_LISTADO);
        result.addObject("usuarios", usuarioServicio.getAllJugadores());
        return result;
	}
	
	@GetMapping("/administradores")
    public ModelAndView mostralTodosLosUsuariosAdministradores() {
        ModelAndView result = new ModelAndView(USUARIO_LISTADO);
        result.addObject("usuarios", usuarioServicio.getAllAdministradores());
        return result;
	}
	
	@GetMapping("/delete/{id}")
    public ModelAndView eliminarUsuariosPorId(@PathVariable("id") Long id) {
        usuarioServicio.deleteUsuario(id);
        return mostralTodosLosUsuarios();
    }
	
	@GetMapping("/edit/{id}")
    public ModelAndView editRoom(@PathVariable("id") Long id) {
        ModelAndView result = new ModelAndView(USUARIO_EDICION);
        result.addObject("usuario", usuarioServicio.mostrarUsuariosPorId(id));
        return result;
    }
	
    @PostMapping("/edit/{id}")
    public ModelAndView editRoom(@PathVariable("id") Long id, @Valid Usuario user,BindingResult br) {        
        ModelAndView result=null;
        if(br.hasErrors()) {
            result = new ModelAndView(USUARIO_EDICION);
            result.addAllObjects(br.getModel());
        }else {
            Usuario usuario = usuarioServicio.mostrarUsuariosPorId(id);
            if(usuario !=null) {
                BeanUtils.copyProperties(usuario, user,"id");
                usuarioServicio.saveUsuario(user);
                result = mostralTodosLosUsuarios();
                result.addObject("message", "Usuario creado satisfactoriamente");
                result = new ModelAndView("Bienvenido");
               
            }else {
                result = mostralTodosLosUsuarios();
                result.addObject("message", "Usuario con id "+id+" no ha sido creado satisfactoriamente");
            }
           
            
        }
        return result;
        
    }
    
    @GetMapping("/create")
    public ModelAndView createUsuario() {
        ModelAndView result = new ModelAndView(USUARIO_EDICION);
        Usuario usuario = new Usuario();
        result.addObject("usuarios", usuario);
        return result;
        
    }
    
    
    @PostMapping("/create")
    public ModelAndView saveNewUsuario(@Valid Usuario usuario,BindingResult br) {
        ModelAndView result=null;
        if(br.hasErrors()) {
            result = new ModelAndView(USUARIO_EDICION);
            result.addAllObjects(br.getModel());
        }else {
            usuarioServicio.saveUsuario(usuario);
            result = mostralTodosLosUsuarios();
            result.addObject("message", "Usuario creado satisfactoriamente");
            result = new ModelAndView("Bienvenido");
            
        }
        return result;
    }

}
