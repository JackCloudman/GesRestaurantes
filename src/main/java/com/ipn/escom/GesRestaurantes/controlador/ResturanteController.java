package com.ipn.escom.GesRestaurantes.controlador;

import com.ipn.escom.GesRestaurantes.modelo.Comentario;
import com.ipn.escom.GesRestaurantes.modelo.Restaurante;
import com.ipn.escom.GesRestaurantes.modelo.Usuario;
import com.ipn.escom.GesRestaurantes.services.ComentarioService;
import com.ipn.escom.GesRestaurantes.services.RestauranteService;
import com.ipn.escom.GesRestaurantes.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/restaurantes")
public class ResturanteController {
    @Autowired
    private RestauranteService restauranteService;
    @Autowired
    private ComentarioService comentarioService;
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
        List<Comentario> comentarios = comentarioService.getComentarios(r);

        Usuario u = new Usuario();
        u.setUsername(authentication.getName());
        u = usuarioService.getUser(u);
        System.out.println(r);

        Comentario userComment = comentarioService.getUserComment(r, u);
        System.out.println(userComment);
        //System.out.println(usuario);
        model.addAttribute("restaurante",r);
        model.addAttribute("comentarios",comentarios);
        return "restauranteInfo";
    }
}
