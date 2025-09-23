package com.cristiansantos.AhorcadoSpring.service;

import com.cristiansantos.AhorcadoSpring.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ValidacionUsuario {

    private final UsuarioRepository usuarioRepository;

    @Autowired
    public ValidacionUsuario(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public boolean usuarioNoExiste(String nombre) {
        return !usuarioRepository.existsByNombreIgnoreCase(nombre);
    }
}