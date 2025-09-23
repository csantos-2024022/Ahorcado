package com.cristiansantos.AhorcadoSpring.service;

import com.cristiansantos.AhorcadoSpring.model.Usuario;
import com.cristiansantos.AhorcadoSpring.repository.UsuarioRepository;
import com.cristiansantos.AhorcadoSpring.service.UsuarioService;
import com.cristiansantos.AhorcadoSpring.service.ValidacionUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.List;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final ValidacionUsuario validacionUsuario;

    @Autowired
    public UsuarioServiceImpl(UsuarioRepository usuarioRepository, ValidacionUsuario validacionUsuario) {
        this.usuarioRepository = usuarioRepository;
        this.validacionUsuario = validacionUsuario;
    }

    @Override
    public Usuario saveUsuario(Usuario usuario) {
        if (usuario == null || usuario.getNombre() == null || usuario.getNombre().trim().isEmpty()) {
            System.out.println("Error: El nombre de usuario no puede ser nulo o vacío.");
            return null;
        }

        if (!validacionUsuario.usuarioNoExiste(usuario.getNombre())) {
            System.out.println("Error: El usuario '" + usuario.getNombre() + "' ya existe.");
            return null;
        }

        return usuarioRepository.save(usuario);
    }

    @Override
    public Optional<Usuario> getUsuarioByCodigo(Long codigoUsuario) {
        return usuarioRepository.findById(codigoUsuario);
    }

    @Override
    public List<Usuario> getAllUsuarios() {
        return usuarioRepository.findAll();
    }

    // Lógica para actualizar un usuario
    @Override
    public Usuario updateUsuario(Long codigoUsuario, Usuario usuario) {
        Optional<Usuario> existingUsuario = usuarioRepository.findById(codigoUsuario);
        if (existingUsuario.isPresent()) {
            Usuario usuarioToUpdate = existingUsuario.get();
            usuarioToUpdate.setNombre(usuario.getNombre());
            usuarioToUpdate.setPass(usuario.getPass());
            return usuarioRepository.save(usuarioToUpdate);
        }
        return null; // Devuelve null si no se encuentra el usuario
    }

    // Lógica para eliminar un usuario
    @Override
    public boolean deleteUsuario(Long codigoUsuario) {
        if (usuarioRepository.existsById(codigoUsuario)) {
            usuarioRepository.deleteById(codigoUsuario);
            return true;
        }
        return false; // Devuelve false si el usuario no existe
    }
}