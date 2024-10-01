package utn.Tienda_Libros.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import UTN.Tienda_Libros.modelo.Libro;

public interface LibroRepositorio extends JpaRepository<Libro, Integer>{

    
} 