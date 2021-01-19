package com.ipn.escom.GesRestaurantes.modelo;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity(name="usuarios")
@Data
public class Usuario implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id; // Each phone will be given an auto-generated unique identifier when stored

    @Column
    private String name; // Save the name of the phone

    @Column
    private String lastname; // Save the operating system running in the phone

    @Column(unique = true)
    private String username; // Save the operating system running in the phone

    @Column
    private String password;

    @Column
    private String role;
    // Standard getters and setters
    @Column
    private boolean enabled;

    @Transient
    public String getFullName() {
        return name+" "+lastname;
    }
}
