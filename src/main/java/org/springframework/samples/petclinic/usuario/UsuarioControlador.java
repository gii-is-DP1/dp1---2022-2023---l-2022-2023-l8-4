package org.springframework.samples.petclinic.usuario;

import java.time.LocalDate;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
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
    public ModelAndView eliminarUsuariosPorId(@PathVariable("id") Integer id) {
        usuarioServicio.deleteUsuario(id);
        return mostralTodosLosUsuarios();
    }

	@GetMapping("/edit/{id}")
    public String editUser(@PathVariable("id") Integer id, ModelMap model) {
        model.put("usuario", usuarioServicio.mostrarUsuariosPorId(id) );
        return USUARIO_EDICION;
    }

    @PostMapping("/edit/{id}")
    public String addUser(@PathVariable("id") Integer id, @Valid Usuario usuario2, BindingResult br, ModelMap model) {
        if( br.hasErrors() )
        {
            System.out.println(br.getAllErrors());
            model.put( "usuario", usuario2 );
            return USUARIO_EDICION;
        }
        else
        {
            Usuario usuario = usuarioServicio.mostrarUsuariosPorId(id);
            if (usuario != null) {
                BeanUtils.copyProperties(usuario2, usuario, "id", "fechaNacimiento", "ultimoInicioSesion", "fecha_modificacion", "fecha_registro", "jugadores", "creador_id");
                usuarioServicio.saveUsuario(usuario);
                return USUARIO_LISTADO;
            } else {
                return USUARIO_EDICION;
            }
        }
    }

    @GetMapping("/create")
    public ModelAndView createUsuario() {
        ModelAndView result = new ModelAndView(USUARIO_EDICION);
        Usuario usuario = new Usuario();
        result.addObject("usuario", usuario);
        return result;
    }


    @PostMapping("/create")
    public ModelAndView saveNewUsuario(@Valid Usuario usuario,BindingResult br) {
        ModelAndView result=null;
        if(br.hasErrors()) {
            result = new ModelAndView(USUARIO_EDICION);
            result.addAllObjects(br.getModel());
        }else {
        	usuario.setFechaRegistro(LocalDate.now());
        	usuario.setFechaModificacion(LocalDate.now());
        	usuario.setUltimoInicioSesion(LocalDate.now());

            usuarioServicio.saveUsuario(usuario);
            result = mostralTodosLosUsuarios();
            result.addObject("message", "Usuario creado satisfactoriamente");
            result = new ModelAndView("Bienvenido");

        }
        return result;
    }

}
