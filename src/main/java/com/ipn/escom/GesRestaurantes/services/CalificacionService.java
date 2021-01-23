package com.ipn.escom.GesRestaurantes.services;

import com.ipn.escom.GesRestaurantes.modelo.Calificacion;
import com.ipn.escom.GesRestaurantes.modelo.Restaurante;
import com.ipn.escom.GesRestaurantes.modelo.Usuario;
import com.ipn.escom.GesRestaurantes.repositorio.CalificacionDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CalificacionService {
    @Autowired
    private CalificacionDAO calificacionDAO;

    public List<Calificacion> getComentarios(Restaurante restaurante){
        return calificacionDAO.findAllByRestauranteOrderByFechaDesc(restaurante);
    }
    public Calificacion getUserComment(Restaurante r, Usuario u){
        return calificacionDAO.findByRestauranteAndUsuario(r,u);
    }
    public Calificacion guardar(Calificacion c){

        c.setFecha(new Date());
        return calificacionDAO.save(c);
    }
}
