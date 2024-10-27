package utn.tienda_libros;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import utn.tienda_libros.vista.LibroFrom;

import javax.swing.*;
import java.awt.*;

@SpringBootApplication
public class TiendaLibrosApplication {

	public static void main(String[] args) {

		ConfigurableApplicationContext contextoSpring =
				new SpringApplicationBuilder(TiendaLibrosApplication.class)
						.headless(false)
						.web(WebApplicationType.NONE)
						.run(args);

		// Configuración de Nimbus Look and Feel
		try {
			for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					UIManager.setLookAndFeel(info.getClassName());  // Aplica Nimbus Look and Feel
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();  // Manejo de errores en caso de que no se pueda aplicar Nimbus
		}

		// Personalizamos los colores y la fuente de Nimbus
		UIManager.put("control", new Color(210, 230, 255)); // Azul claro (fondo general)
		UIManager.put("nimbusBlueGrey", new Color(101, 67, 33)); // Marrón oscuro (bordes y controles)
		UIManager.put("info", new Color(224, 255, 224)); // Verde suave para tooltips
		UIManager.put("nimbusLightBackground", new Color(240, 240, 220)); // Beige claro para componentes
		// Cambiamos la fuente predeterminada
		Font font = new Font("Arial", Font.PLAIN, 14);
		UIManager.put("defaultFont", font);

		// Ajustes adicionales para botones
		UIManager.put("Button.background", new Color(200, 200, 200));  // Color de fondo de los botones
		UIManager.put("Button.foreground", Color.BLACK);  // Texto negro en los botones
		UIManager.put("Button.border", BorderFactory.createLineBorder(new Color(150, 150, 150)));  // Borde gris oscuro

		//Ejecutamos el código para cargar el formulario
		EventQueue.invokeLater(() -> {
			// Obtenemos el método form a través del spring
			LibroFrom libroFrom = contextoSpring.getBean(LibroFrom.class);
			libroFrom.setVisible(true);
		});
		}
}
