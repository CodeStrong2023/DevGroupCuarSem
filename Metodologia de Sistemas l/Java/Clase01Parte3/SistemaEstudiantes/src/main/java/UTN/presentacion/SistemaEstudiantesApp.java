package UTN.presentacion;

import UTN.conexion.Conexion;
import UTN.datos.EstudianteDAO;
import UTN.dominio.Estudiante;

import java.util.Scanner;

public class SistemaEstudiantesApp {
    public static void main(String[] args) {

        var salir = false; //recuerden esto ya lo hicimos antes
        var consola = new Scanner(System.in); //Para leer información de la consola
        //Se crea una instancia de la clase servicio, esto lo hacemos fuera del ciclo
        var estudianteDao = new EstudianteDAO(); //Esta instancia debe hacerse una vez
        while(!salir){
            try{
                mostrarMenu();//Mostramos el menu
                //Este será el método que devolverá un booleano
                salir = ejecutarOpciones(consola, estudianteDao);//Este arroja un exception
            } catch(Exception e){
                System.out.println("Ocurrió un error al ejecutar la operación: "+e.getMessage());
            }

        }

    }//Fin Main
    private static void mostrarMenu(){
        System.out.println("""
                    ******* Sistema de Estudiantes *******
                    1. Listar Estudiantes
                    2. Buscar Estudiantes
                    3. Agregar Estudiante
                    4. Modificar Estudiante
                    5. Eliminar Estudiante
                    6. Salir
                    Elige una opcion:
                    """);
    }

    //Método para ejecutar las opciones, va a regresar un valor booleano, ya que es el que puede
    //Modificar el valor de la variable salir, si es verdadero termina el ciclo while
    private static boolean ejecutarOpciones(Scanner consola,EstudianteDAO estudianteDAO){
        var opcion = Integer.parseInt(consola.nextLine());
        var salir = false;
        switch (opcion) {

            case 1 -> {//Listar Estudiantes
                System.out.println("Listado de Estudiantes..");
                // no muestra la información, solo recupera la info y regresa una lista
                var estudiantes = estudianteDAO.listarEstudiantes();//recibe el listado
                //vamos a iterar cada objeto de tipo estudiante
                estudiantes.forEach(System.out::println); //Para imprimir la lista
            }//Fin caso 1
            case 2 ->{//Buscar estudiante por id
                System.out.println("Introduce el id_estudiante a buscar: ");
                var idEstudiante = Integer.parseInt(consola.nextLine());
                var estudiante = new Estudiante(idEstudiante);
                var encontrado = estudianteDAO.buscarEstudiantePorId(estudiante);
                if(encontrado)
                    System.out.println("Estudiante encontrado: "+ estudiante);
                else
                    System.out.println("Estudiante NO encontrado: "+ estudiante);
            } //Fin del caso 2
            case 3 -> {
                System.out.println("Agregar estudiante: ");
                System.out.println("Nombre: ");
                var nombre = consola.nextLine();
                System.out.println("Apellido: ");
                var apellido = consola.nextLine();
                System.out.println("Telefono: ");
                var telefono = consola.nextLine();
                System.out.println("Email:");
                var email = consola.nextLine();
                //Crear  un objeto estudiante(sin id)
                var estudiante = new Estudiante(nombre,apellido,telefono,email);
                var agregado = estudianteDAO.agregarEstudiante(estudiante);
                if(agregado)
                    System.out.println("Estudiante agregado: "+estudiante);
                else
                    System.out.println("Estudiante NO agregado: "+estudiante);
            }
            case 4 -> {
                System.out.println("Modificar Estudiante: ");
                //Aquí lo primero es especificar cuál es el id del objeto a modificar
                System.out.println("Id Estudiante: ");
                var idEstudiante = Integer.parseInt(consola.nextLine());
                System.out.println("Nombre: ");
                var nombre = consola.nextLine();
                System.out.println("Apellido");
                var apellido = consola.nextLine();
                System.out.println("Telefono");
                var telefono = consola.nextLine();
                System.out.println("Email: ");
                var email = consola.nextLine();
                //area del objeto estudiante a modificar
                var estudiante =
                        new Estudiante(idEstudiante,nombre,apellido,telefono,email);
                var modificado = estudianteDAO.modificarEstudiante(estudiante);
                if(modificado)
                    System.out.println("Estudiante modificado: "+ estudiante);
                else
                    System.out.println("Estudiante NO modificado"+ estudiante);
            } //Fin Caso 4
            case 5 -> {//Eliminar estudiante
                System.out.println("Eliminar estudiante: ");
                System.out.println("Id estudiante: ");
                var idEstudiante = Integer.parseInt(consola.nextLine());
                var estudiante = new Estudiante(idEstudiante);
                var eliminado = estudianteDAO.eliminarEstudiante(estudiante);
                if(eliminado)
                    System.out.println("Estudiante eliminado: "+estudiante);
                else
                    System.out.println("Estudiante NO eliminado: "+ estudiante);
            }//Fin caso 5
            case 6 -> {//salir
                System.out.println("Hasta Pronto!!!");
                salir = true;
            }//Fin caso 6
            default -> System.out.println("Opción no reconocida,ingrese otra opción");
        }//Fin Switch
        return salir;

    }

}//Fin Clase