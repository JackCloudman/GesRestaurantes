package com.ipn.escom.GesRestaurantes.repositorio;


import com.ipn.escom.GesRestaurantes.modelo.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriaDAO extends JpaRepository<Categoria, Long> {
}
