package com.ipn.escom.GesRestaurantes.repositorio;

import com.ipn.escom.GesRestaurantes.modelo.Comentario;
import com.ipn.escom.GesRestaurantes.modelo.Restaurante;
import com.ipn.escom.GesRestaurantes.modelo.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ComentarioDAO extends JpaRepository<Comentario, Long> {
    List<Comentario> findAllByRestauranteOrderByFechaDesc(Restaurante restaurante);
    Comentario findByRestauranteAndUsuario(Restaurante r, Usuario u);
}

