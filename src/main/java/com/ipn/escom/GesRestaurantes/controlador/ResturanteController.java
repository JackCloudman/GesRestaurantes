package com.ipn.escom.GesRestaurantes.controlador;

import com.ipn.escom.GesRestaurantes.modelo.Calificacion;
import com.ipn.escom.GesRestaurantes.modelo.Restaurante;
import com.ipn.escom.GesRestaurantes.modelo.Usuario;
import com.ipn.escom.GesRestaurantes.services.CalificacionService;
import com.ipn.escom.GesRestaurantes.services.RestauranteService;
import com.ipn.escom.GesRestaurantes.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/restaurantes")
public class ResturanteController {
    @Autowired
    private RestauranteService restauranteService;
    @Autowired
    private CalificacionService calificacionService;
    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/")
    public String getRestaurantes(Model model) {
        model.addAttribute("restaurantes",restauranteService.getRestaurantes());
        return "restaurantesList";
    }
    @GetMapping("/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model,Authentication authentication){
        Restaurante r = new Restaurante();
        r.setId(id);
        r = restauranteService.getRestaurante(r);
        if(r == null){
            return "redirect:/restaurantes/";
        }
        List<Calificacion> calificaciones = calificacionService.getComentarios(r);

        Usuario u = new Usuario();
        u.setUsername(authentication.getName());
        u = usuarioService.getUser(u);
        System.out.println(r);

        Calificacion userComment = calificacionService.getUserComment(r, u);
        if(userComment == null){
            userComment = new Calificacion();
            userComment.setEstrellas(0);
            userComment.setComentario("");
        }
        //System.out.println(usuario);
        model.addAttribute("restaurante",r);
        model.addAttribute("calificaciones", calificaciones);
        model.addAttribute("calificacionUsuario",userComment);
        return "restauranteInfo";
    }
    @PostMapping("/{id}")
    public String saveComment(Calificacion calificacion, Model model, Authentication authentication, @PathVariable("id") long id ){
        // VALIDACION RESTAURANTE
        Restaurante r = new Restaurante();
        r.setId(id);
        r = restauranteService.getRestaurante(r);
        if(r == null){
            return "redirect:/restaurantes/";
        }
        calificacion.setRestaurante(r);

        // OBTENEMOS USUARIO
        Usuario u = new Usuario();
        u.setUsername(authentication.getName());
        u = usuarioService.getUsuarioAll(u);
        calificacion.setUsuario(u);

        Calificacion userComment = calificacionService.getUserComment(r, u);
        if(userComment != null){
            calificacion.setId(userComment.getId());
        }
        System.out.println(calificacion);
        calificacionService.guardar(calificacion);
        restauranteService.actualizarEstrellas(r);

        return "redirect:/restaurantes/"+id+"/";
    }
}
