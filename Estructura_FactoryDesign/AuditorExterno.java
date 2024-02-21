import org.apache.poi.ss.usermodel.*;

import java.io.*;
import java.util.*;
import java.time.LocalDate;

public class AuditorExterno extends Usuario {
    public AuditorExterno(String nombre, String excelFilePath) throws IOException {
        super(nombre, excelFilePath);
    }

    public void mostrarOpcionesAE() {
        Scanner scanner = new Scanner(System.in);
        int opcion = 0;
        boolean go = true;

        while (go) {
            System.out.println("Menú del Auditor Externo");
            System.out.println("1. Revisar Nota de Estudiante");
            System.out.println("2. Realizar Pago a Docente");
            System.out.println("3. Consultar Pagos a Docentes");
            System.out.println("4. Salir");
            System.out.println("Indique la opción seleccionada: ");

            try {
                opcion = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("La opción seleccionada no es válida");
                continue;
            }

            switch (opcion) {
                case 1:
                    consultarNotaEstudiante();
                    break;
                case 2:
                    realizarPagoDocente();
                    break;
                case 3:
                    consultarPagosDocentes();
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

    private void consultarNotaEstudiante() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ingrese el nombre del estudiante para consultar la nota:");
        String nombreEstudiante = scanner.nextLine();

        Sheet sheet = getSheetByName("ExcelFactory");
        for (Row row : sheet) {
            if (row.getCell(0).getStringCellValue().equals(nombreEstudiante)) {
                double nota = row.getCell(2).getNumericCellValue();
                System.out.println("La nota de " + nombreEstudiante + " es: " + nota);
                return;
            }
        }

        System.out.println("No se encontró al estudiante con el nombre proporcionado.");
    }

    private void realizarPagoDocente() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ingrese el nombre del docente para realizar el pago:");
        String nombreDocente = scanner.nextLine();

        Random rand = new Random();
        double pagoDocente = 1000 + (5000 - 1000) * rand.nextDouble();

        System.out.println("Se le debe pagar a " + nombreDocente + " la cantidad de " + pagoDocente);
        System.out.println("¿Quiere realizar el pago? (Y/N): ");
        String respuesta = scanner.nextLine();

        if (respuesta.equalsIgnoreCase("Y")) {
            guardarPagoDocente(nombreDocente, pagoDocente);
        } else if (respuesta.equalsIgnoreCase("N")) {
            System.out.println("No se ha realizado el pago.");
        } else {
            System.out.println("Por favor, ingresa una respuesta válida (Y/N)");
        }

        scanner.close();
    }

    private void guardarPagoDocente(String nombreDocente, double cantidad) {
        Sheet sheet = getSheetByName("ExcelFactory");
        for (Row row : sheet) {
            if (row.getCell(4).getStringCellValue().equals(nombreDocente)) {
                row.createCell(7).setCellValue(nombreDocente);
                row.createCell(8).setCellValue(cantidad);
                System.out.println("Pago realizado con éxito.");
                return;
            }
        }

        System.out.println("No se encontró al docente con el nombre proporcionado.");
    }

    private void consultarPagosDocentes() {
        Sheet sheet = getSheetByName("ExcelFactory");

        System.out.println("Listado de Pagos a Docentes:");
        for (Row row : sheet) {
            Cell nombrePagoCell = row.getCell(7);
            Cell cantidadCell = row.getCell(8);

            if (nombrePagoCell != null && cantidadCell != null) {
                String nombrePago = nombrePagoCell.getStringCellValue();
                double cantidad = cantidadCell.getNumericCellValue();
                System.out.println("Docente: " + nombrePago + ", Cantidad: " + cantidad);
            }
        }
    }
}
