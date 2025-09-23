package com.cristiansantos.AhorcadoSpring.Controller;

import com.cristiansantos.AhorcadoSpring.model.Usuario;
import com.cristiansantos.AhorcadoSpring.service.UsuarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping
    public List<Usuario> getAllUsuarios() {
        return usuarioService.getAllUsuarios();
    }

    @GetMapping("/{codigoUsuario}")
    public ResponseEntity<Usuario> getUsuarioByCodigo(@PathVariable Long codigoUsuario) {
        Optional<Usuario> usuario = usuarioService.getUsuarioByCodigo(codigoUsuario);
        return usuario.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> createUsuario(@RequestBody Usuario usuario) {
        Usuario savedUsuario = usuarioService.saveUsuario(usuario);
        if (savedUsuario != null) {
            return new ResponseEntity<>(savedUsuario, HttpStatus.CREATED);
        } else {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Ese usuario ya existe");
        }
    }

    @PutMapping("/{codigoUsuario}")
    public ResponseEntity<?> updateUsuario(@PathVariable Long codigoUsuario, @RequestBody Usuario usuario) {
        Usuario updatedUsuario = usuarioService.updateUsuario(codigoUsuario, usuario);
        if (updatedUsuario != null) {
            return ResponseEntity.ok(updatedUsuario);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Registro no encontrado");
    }

    @DeleteMapping("/{codigoUsuario}")
    public ResponseEntity<String> deleteUsuario(@PathVariable Long codigoUsuario) {
        boolean deleted = usuarioService.deleteUsuario(codigoUsuario);
        if (deleted) {
            return ResponseEntity.ok("Registro eliminado correctamente.");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se puede eliminar porque el registro no fue encontrado.");
    }
}