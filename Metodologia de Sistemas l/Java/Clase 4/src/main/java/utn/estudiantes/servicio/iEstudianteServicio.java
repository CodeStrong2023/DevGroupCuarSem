package utn.estudiantes.servicio;

import utn.estudiantes.modelo.Estudiante;

import java.util.List;

public interface iEstudianteServicio {
    public List<Estudiante>ListarEstudiante();
    public Estudiante BuscarEstudiantePorId(Integer idEstudiante);
    public void guardarEstudiante(Estudiante estudiante);
    public void eliminarEstudiante(Estudiante estudiante);
}
