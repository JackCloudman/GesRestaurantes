package com.ipn.escom.GesRestaurantes.services;

import com.ipn.escom.GesRestaurantes.modelo.Calificacion;
import com.ipn.escom.GesRestaurantes.modelo.Categoria;
import com.ipn.escom.GesRestaurantes.modelo.Restaurante;
import com.ipn.escom.GesRestaurantes.repositorio.CategoriaDAO;
import com.ipn.escom.GesRestaurantes.repositorio.CalificacionDAO;
import com.ipn.escom.GesRestaurantes.repositorio.RestauranteDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RestauranteService {

    @Autowired
    private RestauranteDAO restauranteDAO;
    @Autowired
    private CategoriaDAO categoriaDAO;
    @Autowired
    private CalificacionDAO calificacionDAO;

    public List<Restaurante> getRestaurantes() {
        return restauranteDAO.findAll();
    }
    public Restaurante registrar(Restaurante r){
        try {
            return restauranteDAO.save(r);
        }catch(Exception e){
            return null;
        }
    }
    public List<Categoria> getCategorias(){
        return categoriaDAO.findAll();
    }
    public Restaurante getRestaurante(Restaurante r){
        try {
            r = restauranteDAO.getOne(r.getId());
            if(r.getFoto() == null){
                return null;
            }
            return r;
        }catch (Exception e){
            return null;
        }
    }
    public Categoria registrarCategoria(Categoria c){
        return categoriaDAO.save(c);
    }
    public void actualizarRestaurante(Restaurante r){
        restauranteDAO.save(r);
    }
    public void actualizarEstrellas(Restaurante r){
        List<Calificacion> calificaciones = calificacionDAO.findAllByRestauranteOrderByFechaDesc(r);
        float estrellas = 0;
        for (Calificacion c:
             calificaciones) {
            estrellas+=c.getEstrellas();
        }
        if(estrellas>0){
            estrellas = estrellas/calificaciones.size();
        }
        r.setEstrellas(estrellas);
        actualizarRestaurante(r);
    }
    public void eliminarRestaurante(Restaurante r){
        restauranteDAO.delete(r);
    }
    public void eliminarCategoria(Categoria c){
        categoriaDAO.delete(c);
    }
}