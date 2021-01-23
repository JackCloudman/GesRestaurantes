package com.ipn.escom.GesRestaurantes.modelo;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity(name="calificaciones")
@Data
public class Calificacion {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "restaurante_id", referencedColumnName = "id")
    private Restaurante restaurante;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "usuario_id", referencedColumnName = "id")
    private Usuario usuario;

    private String comentario;

    private Integer estrellas;

    @Column(name="fecha", columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Date fecha;
}
