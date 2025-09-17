package com.cristiansantos.AhorcadoSpring.model;

import jakarta.persistence.*;

@Entity
@Table(name = "palabras")
public class Palabra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codigoPalabra;

    @Column(name = "palabra", nullable = false, unique = true)
    private String palabra;

    @Column(name = "pista", nullable = false)
    private String pista;

    public Palabra() {
    }

    public Palabra(String palabra, String pista) {
        this.palabra = palabra;
        this.pista = pista;
    }

    // Getters y Setters
    public Long getCodigoPalabra() {
        return codigoPalabra;
    }

    public void setCodigoPalabra(Long codigoPalabra) {
        this.codigoPalabra = codigoPalabra;
    }

    public String getPalabra() {
        return palabra;
    }

    public void setPalabra(String palabra) {
        this.palabra = palabra;
    }

    public String getPista() {
        return pista;
    }

    public void setPista(String pista) {
        this.pista = pista;
    }
}