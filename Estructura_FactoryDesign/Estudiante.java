import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.*;
import java.time.LocalDate;

public class Estudiante extends Usuario {
    private double pago;

    public Estudiante(String nombre, String excelFilePath) throws IOException {
        super(nombre, excelFilePath);
        this.pago = 0;
    }

    public void mostrarOpcionesE() {
        Scanner scanner = new Scanner(System.in);
        int opcion = 0;
        boolean go = true;

        while (go) {
            System.out.println("Menú del Estudiante");
            System.out.println("1. Consultar nota de una clase específica");
            System.out.println("2. Realizar Pago");
            System.out.println("3. Consultar Pagos");
            System.out.println("4. Salir");

            try {
                opcion = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("La opción seleccionada no es válida");
                continue;
            }

            switch (opcion) {
                case 1:
                    // Consultar nota (Estudiantes)
                    consultarNota(nombre);
                    break;
                case 2:
                    // Realizar pago (Estudiantes)
                    realizarPago();
                    break;
                case 3:
                    // Consultar pagos (Pagos)

                    break;
                case 4:
                    System.out.println("Saliendo del programa...");
                    go = false;
                    break;
                default:
                    System.out.println("La opción seleccionada es inválida.");
            }
        }
    }

    public void consultarNota(String clase) {
        Sheet sheet = getSheetByName("Estudiante");
        for (Row row : sheet) {
            if (row.getCell(0).getStringCellValue().equals(this.nombre)
                    && row.getCell(1).getStringCellValue().equals(clase)) {
                System.out.println("Tu nota para la clase " + clase + " es: " + row.getCell(2).getNumericCellValue());
                break;
            }
        }
    }

    public void realizarPago() {
        Random rand = new Random();
        double cantidad = 1000 + (5000 - 1000) * rand.nextDouble();
        this.pago += cantidad;
        Scanner scanner = new Scanner(System.in);
        System.out.println("¿Quiere realizar el pago de" + cantidad + "? (Y/N): ");
        String respuesta = scanner.nextLine();

        if (respuesta.equalsIgnoreCase("Y")) {
            System.out.println("El pago se ha realizado con éxito!");
            guardarPago(cantidad);
        } else if (respuesta.equalsIgnoreCase("N")) {
            System.out.println("No se ha realizado el pago");
        } else {
            System.out.println("Por favor, ingresa una respuesta válida (Y/N)");
        }

        scanner.close();
    }

    private void guardarPago(double cantidad) {
        Sheet sheet = getSheetByName("Pagos");
        int lastRowNum = sheet.getLastRowNum();
        Row row = sheet.createRow(lastRowNum + 1);
        Cell cell = row.createCell(0);
        cell.setCellValue(this.nombre);
        cell = row.createCell(1);
        cell.setCellValue(cantidad);
        cell = row.createCell(2);
        cell.setCellValue(LocalDate.now().toString());
    }
}
