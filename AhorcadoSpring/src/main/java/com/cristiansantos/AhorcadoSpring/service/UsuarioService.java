package com.cristiansantos.AhorcadoSpring.service;

import com.cristiansantos.AhorcadoSpring.model.Usuario;
import java.util.List;
import java.util.Optional;

public interface UsuarioService {

    List<Usuario> getAllUsuarios();

    Optional<Usuario> getUsuarioByCodigo(Long codigoUsuario);

    Usuario saveUsuario(Usuario usuario);

    Usuario updateUsuario(Long codigoUsuario, Usuario usuario);
    boolean deleteUsuario(Long codigoUsuario);
}