package com.sistemaestudiantedao.service;

import com.sistemaestudiantedao.model.Estudiante;

import java.util.List;

public interface iEstudianteServicio {
    public List<Estudiante> listarEstudiantes();
    public Estudiante BuscarEstudiantePorId(Integer idEstudiante);
    public void guardarEstudiante(Estudiante estudiante);
    public void eliminarEstudiante(Estudiante estudiante);
}
