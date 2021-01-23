package com.ipn.escom.GesRestaurantes.services;

import com.ipn.escom.GesRestaurantes.modelo.Usuario;
import com.ipn.escom.GesRestaurantes.repositorio.UsuarioDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioDAO usuarioDAO;

    public Usuario getUser(Usuario u){
        Usuario user = usuarioDAO.getByUsername(u.getUsername());
        user.setPassword(null);
        return user;
    }
    public Usuario getUsuarioAll(Usuario u){
        Usuario user = usuarioDAO.getByUsername(u.getUsername());
        return user;
    }
    public List<Usuario> list() {
        return usuarioDAO.findAll();
    }
    public Boolean registrar(Usuario u){
        u.setRole("ROLE_USER");
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        u.setPassword(passwordEncoder.encode(u.getPassword()));
        u.setEnabled(true);
        try {
            usuarioDAO.save(u);
            return true;
        }catch(Exception e){
            return false;
        }
    }
}
