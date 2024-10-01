package utn.Tienda_Libros.servicio;

import java.util.List;

import UTN.Tienda_Libros.modelo.Libro;

public interface ILibroServicio {
    
    public List<Libro> listaLibros();
    public Libro buscarLibroPorId(Integer idLibro);
    public void guardarLibro(Libro libro);
    public void eliminarLibro(Libro libro);
}
