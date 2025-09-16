package com.cristiansantos.AhorcadoSpring.service;

import com.cristiansantos.AhorcadoSpring.model.Usuario;
import java.util.List;
import java.util.Optional;

public interface UsuarioService {
    List<Usuario> getAllUsuarios();
    Optional<Usuario> getUsuarioByCodigo(Integer codigoUsuario);
    Usuario saveUsuario(Usuario usuario);
    Usuario updateUsuario(Integer codigoUsuario, Usuario usuario);
    void deleteUsuario(Integer codigoUsuario);
}
