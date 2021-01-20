package com.ipn.escom.GesRestaurantes.services;

import com.ipn.escom.GesRestaurantes.modelo.Categoria;
import com.ipn.escom.GesRestaurantes.modelo.Restaurante;
import com.ipn.escom.GesRestaurantes.modelo.Usuario;
import com.ipn.escom.GesRestaurantes.repositorio.CategoriaDAO;
import com.ipn.escom.GesRestaurantes.repositorio.RestauranteDAO;
import com.ipn.escom.GesRestaurantes.repositorio.UsuarioDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RestauranteService {

    @Autowired
    private RestauranteDAO restauranteDAO;
    @Autowired
    private CategoriaDAO categoriaDAO;

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
}