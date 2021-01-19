package com.ipn.escom.GesRestaurantes.controlador;

import com.ipn.escom.GesRestaurantes.modelo.Usuario;
import com.ipn.escom.GesRestaurantes.repositorio.UsuarioDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UsuarioController {
    @Autowired
    private UsuarioDAO usuarioDAO;
    @GetMapping("/")
    public List<Usuario> getAllPhones() {
        return usuarioDAO.findAll();
    }
    // POST method to create a phone
    @PostMapping("/")
    public Usuario createUsuario(@Valid @RequestBody Usuario usuario) {
        return usuarioDAO.save(usuario);
    }

}
