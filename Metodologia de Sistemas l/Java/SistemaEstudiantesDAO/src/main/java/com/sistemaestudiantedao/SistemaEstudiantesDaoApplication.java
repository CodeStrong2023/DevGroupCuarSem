package com.sistemaestudiantedao;

import com.sistemaestudiantedao.model.Estudiante;
import com.sistemaestudiantedao.service.EstudianteServicio;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
import java.util.Scanner;

@SpringBootApplication
public class SistemaEstudiantesDaoApplication implements CommandLineRunner {

	@Autowired
	private EstudianteServicio estudianteServicio;
	private static final Logger logger =
			LoggerFactory.getLogger(SistemaEstudiantesDaoApplication.class);
	String nl = System.lineSeparator();

	public static void main(String[] args) {
	logger.info("Iniciando la aplicación...");
	//Levanta la fábrica de Spring
		SpringApplication.run(SistemaEstudiantesDaoApplication.class, args);
		logger.info("Aplicación Finalizada!!");
	}

	@Override
	public void run(String... args) throws Exception {
		logger.info(nl+"Ejecutando el método run de Spring..."+nl);
		boolean salir = false;
		Scanner consola = new Scanner(System.in);
		while(!salir){
			mostrarMenu();
			salir = ejecutarOpciones(consola);
			logger.info(nl);

		}
	}
	private void mostrarMenu(){
		logger.info(nl);
		logger.info("""
				********* Sistema Estudiantes *********
				1. Listar Estudiantes
				2. Buscar Estudiantes
				3. Agregar Estudiante
				4. Modificar Estudiante
				5. Eliminar Estudiante
				6. Salir
				Eliga una opción:""");
	}
	private boolean ejecutarOpciones(Scanner consola){
		int opcion = Integer.parseInt(consola.nextLine());
		boolean salir = false;
		switch (opcion){
			case 1 -> {
				//Listamos estudiantes
				logger.info(nl + "Listado de estudiantes: " + nl);
				List<Estudiante> estudiantes = estudianteServicio.listarEstudiantes();
				estudiantes.forEach((estudiante -> logger.info(estudiante.toString()+nl)));
			}
			case 2 -> {
				//Buscamos por ID
				logger.info("Digite el ID estudiante a buscar: ");
				int idEstudiante = Integer.parseInt(consola.nextLine());
				Estudiante estudiante = estudianteServicio.BuscarEstudiantePorId(idEstudiante);
				if(estudiante != null)
					logger.info("Estudiante encontrado: " + estudiante + nl);
				else
					logger.info("Estudiante NO encontrado: "+ estudiante+nl);
			}
			case 3 -> {
				//Agregamos estudiante
				logger.info("Agregar Estudiante: "+nl);
				logger.info("Nombre: ");
				String nombre = consola.nextLine();
				logger.info("Apellido: ");
				String apellido = consola.nextLine();
				logger.info("Teléfono: ");
				String telefono = consola.nextLine();
				logger.info("Email: ");
				String email = consola.nextLine();
				//Crear el objeto estudiante sin el ID
				Estudiante estudiante = new Estudiante();
				estudiante.setNombre(nombre);
				estudiante.setApellido(apellido);
				estudiante.setTelefono(telefono);
				estudiante.setEmail(email);
				estudianteServicio.guardarEstudiante(estudiante);
				logger.info("Estudiante agregado: " + estudiante+nl);
			}
			case 4 -> {
				//Modificar estudiante
				logger.info("Modificar estudiante: " + nl);
				logger.info("Ingrese el ID estudiante: ");
				int idEstudiante = Integer.parseInt(consola.nextLine());
				//Buscamos el estudiante a modificar
				Estudiante estudiante = estudianteServicio.BuscarEstudiantePorId(idEstudiante);
				if(estudiante!= null){
					logger.info("Nombre: ");
					String nombre = consola.nextLine();
					logger.info("Apellido: ");
					String apellido = consola.nextLine();
					logger.info("Teléfono: ");
					String telefono = consola.nextLine();
					logger.info("Email: ");
					String email = consola.nextLine();
					estudiante.setNombre(nombre);
					estudiante.setApellido(apellido);
					estudiante.setTelefono(telefono);
					estudiante.setEmail(email);
					estudianteServicio.guardarEstudiante(estudiante);
					logger.info("Estudiante modificado: " + estudiante+nl);
				}
				else{
					logger.info("Estudiante NO encontrado con el ID: " + idEstudiante+nl);
				}
			}
			case 5 -> {
				//Eliminamos estudiante
				logger.info("Eliminar estudiante: " + nl);
				logger.info("Digite el ID estudiante: ");
				int idEstudiante = Integer.parseInt(consola.nextLine());
				//Buscamos el ID estudiante a ELIMINAR
				Estudiante estudiante = estudianteServicio.BuscarEstudiantePorId(idEstudiante);
				if(estudiante != null){
					estudianteServicio.eliminarEstudiante(estudiante);
					logger.info("Estudiante eliminado: " + estudiante+nl);
				}
				else
					logger.info("Estudiante NO encontrado con ID:" + estudiante+nl);
			}
			case 6 -> {
				//Salir
				logger.info("Hasta Pronto!!"+nl+nl);
				salir = true;
			}
			default -> logger.info("Opcion no reconocida: " + opcion+nl);

		}//Fin del SWITCH
		return salir;
	}//Fin del método ejecutarOpciones
}
