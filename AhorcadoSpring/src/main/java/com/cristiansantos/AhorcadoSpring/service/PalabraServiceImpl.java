package com.cristiansantos.AhorcadoSpring.service;

import com.cristiansantos.AhorcadoSpring.model.Palabra;
import com.cristiansantos.AhorcadoSpring.repository.PalabraRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class PalabraServiceImpl implements PalabraService {

    private final PalabraRepository palabraRepository;

    public PalabraServiceImpl(PalabraRepository palabraRepository) {
        this.palabraRepository = palabraRepository;
    }

    @Override
    public List<Palabra> getAllPalabras() {
        return palabraRepository.findAll();
    }

    @Override
    public Optional<Palabra> getPalabraByCodigo(Integer codigoPalabra) {
        return palabraRepository.findById(codigoPalabra);
    }

    @Override
    public Palabra savePalabra(Palabra palabra) {
        return palabraRepository.save(palabra);
    }

    @Override
    public Palabra updatePalabra(Integer codigoPalabra, Palabra palabra) {
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
    public void deletePalabra(Integer codigoPalabra) {
        palabraRepository.deleteById(codigoPalabra);
    }
}
