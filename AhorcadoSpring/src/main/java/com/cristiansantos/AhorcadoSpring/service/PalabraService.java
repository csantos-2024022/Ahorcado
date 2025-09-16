package com.cristiansantos.AhorcadoSpring.service;

import com.cristiansantos.AhorcadoSpring.model.Palabra;
import java.util.List;
import java.util.Optional;

public interface PalabraService {
    List<Palabra> getAllPalabras();
    Optional<Palabra> getPalabraByCodigo(Integer codigoPalabra);
    Palabra savePalabra(Palabra palabra);
    Palabra updatePalabra(Integer codigoPalabra, Palabra palabra);
    void deletePalabra(Integer codigoPalabra);
}
