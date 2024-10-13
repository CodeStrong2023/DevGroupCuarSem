package utn.tienda_libros.vista;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import org.springframework.stereotype.Component;
import utn.tienda_libros.servicio.LibroServicio;

import java.awt.*;

@Component
public class LibroForm extends JFrame {
    LibroServicio libroServicio;
    private JPanel panel;
    private JTable tablaLibros;
    private DefaultTableModel tablaModeloLibro;

    public LibroForm(LibroServicio libroServicio){
        this.libroServicio = libroServicio;
        iniciarForma();
    }

    private void iniciarForma(){
        setContentPane(panel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setSize(900, 700);
        //Para obtener dimenciones de la ventana
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension tamanioPantalla = toolkit.getScreenSize();
        int x = (tamanioPantalla.width - getWidth()/2);
        int y = (tamanioPantalla.height - getHeight()/2);
        setLocation(x, y);
    }

    private void createUIComponents(){
        this.tablaModeloLibro = new DefaultTableModel(0,5);
        String[] cabecera = {"Id", "Libro", "Autor", "Precio", "Existencias"};
        this.tablaModeloLibro.setColumnIdentifiers(cabecera);
        //Instanciar el onjeto de JTable
        this.tablaLibros =new JTable(tablaModeloLibro);
        listarLibros();
    }

    private void listarLibros() {
        // Limpiar la tabla
        tablaModeloLibro.setRowCount(0);
        // Obtener los libros de la BD
        var libros = libroServicio.listarLibros();
        // Iteramos cada libro
        libros.forEach((libro) -> {  // Función lambda
            // Creamos cada registro para agregarlo a la tabla
            Object[] renglonLibro = {
                    libro.getIdLibro(),
                    libro.getNombreLibro(),
                    libro.getAutor(),
                    libro.getPrecio(),
                    libro.getExistencias(),
            };
            this.tablaModeloLibro.addRow(renglonLibro);
        });
      }
    }
