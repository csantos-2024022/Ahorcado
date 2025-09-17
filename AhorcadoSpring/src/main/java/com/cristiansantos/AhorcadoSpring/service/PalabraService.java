package com.cristiansantos.AhorcadoSpring.service;

import com.cristiansantos.AhorcadoSpring.model.Palabra;
import java.util.List;
import java.util.Optional;

public interface PalabraService {
    List<Palabra> getAllPalabras();
    Optional<Palabra> getPalabraByCodigo(Long codigoPalabra);
    Palabra savePalabra(Palabra palabra);
    Palabra updatePalabra(Long codigoPalabra, Palabra palabra);
    boolean deletePalabra(Long codigoPalabra);
}