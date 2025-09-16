package com.cristiansantos.AhorcadoSpring.model;


import jakarta.persistence.*;

@Entity
@Table(name = "palabras")
public class Palabra {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer codigoPalabra;

    @Column(name = "palabra")
    private String palabra;

    @Column(name = "pista")
    private String pista;


    public Palabra() {
    }

    public Palabra(Integer codigo_Usuario, String palabra, String pista) {
        this.codigoPalabra = codigo_Usuario;
        this.palabra = palabra;
        this.pista = pista;
    }

    public Integer getCodigoPalabra() {
        return codigoPalabra;
    }

    public void setCodigoPalabra(Integer codigoPalabra) {
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
