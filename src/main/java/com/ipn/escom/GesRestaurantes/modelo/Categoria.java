package com.ipn.escom.GesRestaurantes.modelo;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity(name="categorias")
@Data
public class Categoria {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String nombreCategoria;
}

