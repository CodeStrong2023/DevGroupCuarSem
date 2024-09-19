package com.sistemaestudiantedao.service;

import com.sistemaestudiantedao.model.Estudiante;
import com.sistemaestudiantedao.repository.EstudianteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EstudianteServicio implements iEstudianteServicio {
    @Autowired
    private EstudianteRepository estudianteRepository;


    @Override
    public List<Estudiante> listarEstudiantes() {
        List<Estudiante> estudiantes = estudianteRepository.findAll();
        return estudiantes;
    }

    @Override
    public Estudiante BuscarEstudiantePorId(Integer idEstudiante) {
        Estudiante estudiante2024 = estudianteRepository.findById(idEstudiante).orElse(new Estudiante());
        return estudiante2024;

    }

    @Override
    public void guardarEstudiante(Estudiante estudiante) {
        estudianteRepository.delete(estudiante);
    }

    @Override
    public void eliminarEstudiante(Estudiante estudiante) {
        estudianteRepository.delete(estudiante);
    }
}
