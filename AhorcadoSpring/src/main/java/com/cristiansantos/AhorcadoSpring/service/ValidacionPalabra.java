package com.cristiansantos.AhorcadoSpring.service;

import com.cristiansantos.AhorcadoSpring.repository.PalabraRepository;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class ValidacionPalabra {

    private final PalabraRepository palabraRepository;

    @Autowired
    public ValidacionPalabra(PalabraRepository palabraRepository) {
        this.palabraRepository = palabraRepository;
    }

    public boolean palabraNoExiste(String palabra) {

        return !palabraRepository.existsByPalabraIgnoreCase(palabra.toLowerCase());
    }
}