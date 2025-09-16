package com.cristiansantos.AhorcadoSpring.repository;

import com.cristiansantos.AhorcadoSpring.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
}

