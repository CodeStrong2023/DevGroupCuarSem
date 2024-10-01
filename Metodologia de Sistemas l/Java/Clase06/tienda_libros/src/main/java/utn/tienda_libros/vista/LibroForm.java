package utn.Tienda_Libros.vista;

import javax.swing.*;

import UTN.Tienda_Libros.servicio.LibroServicio;

public class LibroForm extends JFrame {
    LibroServicio libroServicio;
    private JPanel panel;

    public LibroForm(LibroServicio libroServicio){
        this.libroServicio = libroServicio;
        iniciarForma();
    }

    private void iniciarForma(){
        setContentPane(panel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setSize(900, 700);
    }
}
