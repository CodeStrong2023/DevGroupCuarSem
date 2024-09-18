package com.sistemaestudiantedao.repository;

import com.sistemaestudiantedao.model.Estudiante;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EstudianteRepository extends JpaRepository<Estudiante,Integer> {
}
