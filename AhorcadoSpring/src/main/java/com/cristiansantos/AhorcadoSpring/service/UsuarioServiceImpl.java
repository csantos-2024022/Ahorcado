package com.cristiansantos.AhorcadoSpring.service;

import com.cristiansantos.AhorcadoSpring.model.Usuario;
import com.cristiansantos.AhorcadoSpring.repository.UsuarioRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioServiceImpl(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public List<Usuario> getAllUsuarios() {
        return usuarioRepository.findAll();
    }

    @Override
    public Optional<Usuario> getUsuarioByCodigo(Integer codigoUsuario) {
        return usuarioRepository.findById(codigoUsuario);
    }

    @Override
    public Usuario saveUsuario(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    @Override
    public Usuario updateUsuario(Integer codigoUsuario, Usuario usuario) {
        Optional<Usuario> existingUsuario = usuarioRepository.findById(codigoUsuario);
        if (existingUsuario.isPresent()) {
            Usuario usuarioToUpdate = existingUsuario.get();
            usuarioToUpdate.setNombre(usuario.getNombre());
            usuarioToUpdate.setPass(usuario.getPass());
            return usuarioRepository.save(usuarioToUpdate);
        }
        return null;
    }

    @Override
    public void deleteUsuario(Integer codigoUsuario) {
        usuarioRepository.deleteById(codigoUsuario);
    }
}

