package com.ipn.escom.GesRestaurantes.services;

import com.ipn.escom.GesRestaurantes.modelo.Comentario;
import com.ipn.escom.GesRestaurantes.modelo.Restaurante;
import com.ipn.escom.GesRestaurantes.modelo.Usuario;
import com.ipn.escom.GesRestaurantes.repositorio.ComentarioDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ComentarioService {
    @Autowired
    private ComentarioDAO comentarioDAO;

    public List<Comentario> getComentarios(Restaurante restaurante){
        return comentarioDAO.findAllByRestauranteOrderByFechaDesc(restaurante);
    }
    public Comentario getUserComment(Restaurante r, Usuario u){
        return comentarioDAO.findByRestauranteAndUsuario(r,u);
    }
}
