package com.ipn.escom.GesRestaurantes.repositorio;

import com.ipn.escom.GesRestaurantes.modelo.Calificacion;
import com.ipn.escom.GesRestaurantes.modelo.Restaurante;
import com.ipn.escom.GesRestaurantes.modelo.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CalificacionDAO extends JpaRepository<Calificacion, Long> {
    List<Calificacion> findAllByRestauranteOrderByFechaDesc(Restaurante restaurante);
    Calificacion findByRestauranteAndUsuario(Restaurante r, Usuario u);
}

