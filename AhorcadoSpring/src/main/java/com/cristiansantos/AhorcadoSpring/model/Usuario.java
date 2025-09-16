    package com.cristiansantos.AhorcadoSpring.model;

import jakarta.persistence.*;

@Entity
@Table(name = "Usuarios")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codigoUsuario")
    private Integer codigoUsuario;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "pass")
    private String pass;

    public Usuario() {
    }

    public Usuario(Integer codigoUsuario, String nombre, String pass) {
        this.codigoUsuario = codigoUsuario;
        this.nombre = nombre;
        this.pass = pass;
    }

    public Integer getCodigoUsuario() {
        return codigoUsuario;
    }

    public void setCodigoUsuario(Integer codigoUsuario) {
        this.codigoUsuario = codigoUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
}
