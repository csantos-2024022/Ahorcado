package com.cristiansantos.AhorcadoSpring.repository;

import com.cristiansantos.AhorcadoSpring.model.Palabra;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PalabraRepository extends JpaRepository<Palabra, Integer> {

}

