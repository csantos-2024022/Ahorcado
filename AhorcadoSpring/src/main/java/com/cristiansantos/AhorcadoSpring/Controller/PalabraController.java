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
    public ResponseEntity<Palabra> getPalabraByCodigo(@PathVariable Long codigoPalabra) {
        Optional<Palabra> palabra = palabraService.getPalabraByCodigo(codigoPalabra);
        return palabra.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> createPalabra(@RequestBody Palabra palabra) {
        Palabra savedPalabra = palabraService.savePalabra(palabra);
        if (savedPalabra != null) {
            return new ResponseEntity<>(savedPalabra, HttpStatus.CREATED);
        } else {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Esa palabra ya existe");
        }
    }

    @PutMapping("/{codigoPalabra}")
    public ResponseEntity<?> updatePalabra(@PathVariable Long codigoPalabra, @RequestBody Palabra palabra) {
        Palabra updatedPalabra = palabraService.updatePalabra(codigoPalabra, palabra);
        if (updatedPalabra != null) {
            return ResponseEntity.ok(updatedPalabra);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Registro no encontrado");
    }

    @DeleteMapping("/{codigoPalabra}")
    public ResponseEntity<String> deletePalabra(@PathVariable Long codigoPalabra) {
        boolean deleted = palabraService.deletePalabra(codigoPalabra);
        if (deleted) {
            return ResponseEntity.ok("Registro eliminado correctamente.");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se puede eliminar porque el registro no fue encontrado.");
    }
}