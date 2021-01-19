package com.ipn.escom.GesRestaurantes.repositorio;


import com.ipn.escom.GesRestaurantes.modelo.Restaurante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RestauranteDAO extends JpaRepository<Restaurante, Long> {
}
