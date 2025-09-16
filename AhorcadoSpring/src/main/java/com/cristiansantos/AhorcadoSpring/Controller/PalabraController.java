package com.cristiansantos.AhorcadoSpring.Controller;

import com.cristiansantos.AhorcadoSpring.model.Palabra;
import com.cristiansantos.AhorcadoSpring.service.PalabraService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/palabras")
public class PalabraController {

    private final PalabraService palabraService;

    public PalabraController(PalabraService palabraService) {
        this.palabraService = palabraService;
    }

    @GetMapping
    public List<Palabra> getAllPalabras() {
        return palabraService.getAllPalabras();
    }

    @GetMapping("/{codigoPalabra}")
    public ResponseEntity<Palabra> getPalabraByCodigo(@PathVariable Integer codigoPalabra) {
        Optional<Palabra> palabra = palabraService.getPalabraByCodigo(codigoPalabra);
        return palabra.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Palabra> createPalabra(@RequestBody Palabra palabra) {
        Palabra savedPalabra = palabraService.savePalabra(palabra);
        return new ResponseEntity<>(savedPalabra, HttpStatus.CREATED);
    }

    @PutMapping("/{codigoPalabra}")
    public ResponseEntity<Palabra> updatePalabra(@PathVariable Integer codigoPalabra, @RequestBody Palabra palabra) {
        Palabra updatedPalabra = palabraService.updatePalabra(codigoPalabra, palabra);
        if (updatedPalabra != null) {
            return ResponseEntity.ok(updatedPalabra);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{codigoPalabra}")
    public ResponseEntity<Void> deletePalabra(@PathVariable Integer codigoPalabra) {
        palabraService.deletePalabra(codigoPalabra);
        return ResponseEntity.noContent().build();
    }
}
