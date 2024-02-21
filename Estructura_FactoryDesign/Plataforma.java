import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.*;
import java.time.LocalDate;

public class Plataforma {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ingrese su nombre de usuario:");
        String nombre = scanner.nextLine();

        UsuarioFactory factory = new UsuarioFactory();
        Usuario usuario = factory.getUsuario(nombre,
                "ExcelFactory.csv");

        if (usuario != null) {
            System.out.println("Bienvenido " + nombre);
            if (usuario instanceof Estudiante) {
                ((Estudiante) usuario).mostrarOpcionesE();
            } else if (usuario instanceof Docente) {
                ((Docente) usuario).mostrarOpcionesD();
            } else if (usuario instanceof PersonalAdministrativo) {
                ((PersonalAdministrativo) usuario).mostrarOpcionesPA();
            } else if (usuario instanceof AuditorExterno) {
                ((AuditorExterno) usuario).mostrarOpcionesAE();
            }
        } else {
            System.out.println("Usuario no Registrado");
        }

        scanner.close();
    }
}
