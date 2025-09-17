package com.cristiansantos.AhorcadoSpring.service;

import com.cristiansantos.AhorcadoSpring.model.Palabra;
import com.cristiansantos.AhorcadoSpring.repository.PalabraRepository;
import com.cristiansantos.AhorcadoSpring.service.PalabraService;
import com.cristiansantos.AhorcadoSpring.service.ValidacionPalabra;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;
import java.util.List;

@Service
public class PalabraServiceImpl implements PalabraService {

    private final PalabraRepository palabraRepository;
    private final ValidacionPalabra validacionPalabra;

    @Autowired
    public PalabraServiceImpl(PalabraRepository palabraRepository, ValidacionPalabra validacionPalabra) {
        this.palabraRepository = palabraRepository;
        this.validacionPalabra = validacionPalabra;
    }

    @Override
    public List<Palabra> getAllPalabras() {
        return palabraRepository.findAll();
    }

    @Override
    public Optional<Palabra> getPalabraByCodigo(Long codigoPalabra) {
        return palabraRepository.findById(codigoPalabra);
    }

    @Override
    public Palabra savePalabra(Palabra palabra) {
        if (palabra == null || palabra.getPalabra() == null || palabra.getPalabra().trim().isEmpty()) {
            System.out.println("Error: La palabra no puede ser nula o vacía.");
            return null;
        }

        if (validacionPalabra.palabraNoExiste(palabra.getPalabra())) {
            palabra.setPalabra(palabra.getPalabra().toUpperCase());
            return palabraRepository.save(palabra);
        } else {
            System.out.println("Error: La palabra '" + palabra.getPalabra() + "' ya existe.");
            return null;
        }
    }

    @Override
    public Palabra updatePalabra(Long codigoPalabra, Palabra palabra) {
        Optional<Palabra> existingPalabra = palabraRepository.findById(codigoPalabra);
        if (existingPalabra.isPresent()) {
            Palabra palabraToUpdate = existingPalabra.get();
            palabraToUpdate.setPalabra(palabra.getPalabra());
            palabraToUpdate.setPista(palabra.getPista());
            return palabraRepository.save(palabraToUpdate);
        }
        return null;
    }

    @Override
    public boolean deletePalabra(Long codigoPalabra) {
        if (palabraRepository.existsById(codigoPalabra)) {
            palabraRepository.deleteById(codigoPalabra);
            return true;
        }
        return false;
    }
}