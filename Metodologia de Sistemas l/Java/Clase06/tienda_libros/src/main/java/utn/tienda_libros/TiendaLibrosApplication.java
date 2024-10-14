package utn.tienda_libros;

import org.springframework.boot.ConfigurableBootstrapContext;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import utn.tienda_libros.vista.LibroForm;

import java.awt.EventQueue;

@SpringBootApplication
public class TiendaLibrosApplication {
	public static void main(String[] args) {
		SpringApplication.run(TiendaLibrosApplication.class, args);

		ConfigurableApplicationContext contextoSpring =
				new SpringApplicationBuilder(TiendaLibrosApplication.class)
						.headless(false)
						.web(WebApplicationType.NONE)
						.run(args);

		//Ejecutamos el codigo para cargar el formulario
		EventQueue.invokeLater(() -> { //Metodo lambda
			//obtenemos elm onjeto from a traves del spring
			LibroForm libroForm = contextoSpring.getBean(LibroForm.class);
			libroForm.setVisible(true);
		});
	}
}
