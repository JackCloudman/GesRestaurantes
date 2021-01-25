package com.ipn.escom.GesRestaurantes.modelo;

import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.data.annotation.Transient;

import javax.persistence.*;

@Entity(name="restaurantes")
@Data
public class Restaurante {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id; // Each phone will be given an auto-generated unique identifier when stored

    private String nombre;
    private String direccion;
    private String descripcion;

    @OnDelete(action = OnDeleteAction.CASCADE)
    @OneToOne
    @JoinColumn(name = "tipo", referencedColumnName = "id")
    private Categoria tipo;

    private String foto;
    private float estrellas = 0;
    //private String horario;

    //private String latitud;
    //private string longitud;
    @Transient
    public String getPhotosImagePath() {
        if (foto == null) return null;

        return "/restaurantes_photos/" + id + "/" + foto;
    }

}
