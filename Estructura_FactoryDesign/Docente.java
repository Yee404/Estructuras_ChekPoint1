import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.*;
import java.time.LocalDate;

public class Docente extends Usuario {
    public Docente(String nombre, String excelFilePath) throws IOException {
        super(nombre, excelFilePath);
    }

    public void mostrarOpcionesD() {
        Scanner scanner = new Scanner(System.in);
        int opcion = 0;
        boolean go = true;

        while (go) {
            System.out.println("Menú de Docentes");
            System.out.println("1. Ingresar notas a Estudiantes");
            System.out.println("2. Realizar Cobro de salario");
            System.out.println("3. Historial de Pagos");
            System.out.println("4. Salir");

            try {
                opcion = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("La opción seleccionada no es válida");
                continue;
            }

            switch (opcion) {
                case 1:
                    // Asignar nota a estudiante, curso, seccion
                    break;
                case 2:
                    // Cobro de Salario
                    break;
                case 3:
                    // Historial de Pagos
                    break;
                case 4:
                    System.out.println("Saliendo del programa...");
                    go = false;
                    break;
                default:
                    System.out.println("La opción seleccionada no es válida.");
            }
        }

        scanner.close();
    }

    //
}
