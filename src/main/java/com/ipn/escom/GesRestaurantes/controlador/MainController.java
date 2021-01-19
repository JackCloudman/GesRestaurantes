package com.ipn.escom.GesRestaurantes.controlador;

import com.ipn.escom.GesRestaurantes.modelo.Usuario;
import com.ipn.escom.GesRestaurantes.repositorio.UsuarioDAO;
import com.ipn.escom.GesRestaurantes.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
public class MainController {
    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/")
    public String index(@RequestParam(name="name",required = false,defaultValue = "World") String name, Model model) {
        model.addAttribute("name",name);
        return "index";
    }
    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @GetMapping("/register")
    public String getRegister(){return "register";}

    @PostMapping("/register")
    public String postRegister(@Valid Usuario usuario,Model model){
        if(usuarioService.registrar(usuario)){
            return "login";
        }
        model.addAttribute("error","El usuario que tratas de registrar ya existe.");
        return "register";
    }
}
