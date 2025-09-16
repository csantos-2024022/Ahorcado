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
    public ResponseEntity<Usuario> getUsuarioByCodigo(@PathVariable Integer codigoUsuario) {
        Optional<Usuario> usuario = usuarioService.getUsuarioByCodigo(codigoUsuario);
        return usuario.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Usuario> createUsuario(@RequestBody Usuario usuario) {
        Usuario savedUsuario = usuarioService.saveUsuario(usuario);
        return new ResponseEntity<>(savedUsuario, HttpStatus.CREATED);
    }

    @PutMapping("/{codigoUsuario}")
    public ResponseEntity<Usuario> updateUsuario(@PathVariable Integer codigoUsuario, @RequestBody Usuario usuario) {
        Usuario updatedUsuario = usuarioService.updateUsuario(codigoUsuario, usuario);
        if (updatedUsuario != null) {
            return ResponseEntity.ok(updatedUsuario);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{codigoUsuario}")
    public ResponseEntity<Void> deleteUsuario(@PathVariable Integer codigoUsuario) {
        usuarioService.deleteUsuario(codigoUsuario);
        return ResponseEntity.noContent().build();
    }
}
