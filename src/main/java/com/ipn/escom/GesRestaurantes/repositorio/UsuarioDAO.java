package com.ipn.escom.GesRestaurantes.repositorio;

import com.ipn.escom.GesRestaurantes.modelo.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioDAO extends JpaRepository<Usuario, Long> {
    Usuario getByUsername(String username);
}
