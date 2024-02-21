import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.*;
import java.time.LocalDate;

public class PersonalAdministrativo extends Usuario {
    public PersonalAdministrativo(String nombre, String excelFilePath) throws IOException {
        super(nombre, excelFilePath);
    }

    public void mostrarOpcionesPA() {
        Scanner scanner = new Scanner(System.in);
        int opcion = 0;
        boolean go = true;

        while (go) {
            System.out.println("Menú del Personal Administrativo");
            System.out.println("1. Crear Cursos, Docentes y Estudiantes");
            System.out.println("2. Asignar estudiantes a cursos");
            System.out.println("3. Asignar catedráticos a cursos");
            System.out.println("4. Asignar pago a catedrático");
            System.out.println("5. Resumen de Notas y Pagos de estudiantes");
            System.out.println("6. Salir");
            System.out.println("Indique la opción seleccionada: ");

            try {
                opcion = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("La opción seleccionada no es válida");
                continue;
            }

            switch (opcion) {
                case 1:
                    Scanner scanner1 = new Scanner(System.in);
                    System.out.println("¿Desea agregar un nuevo estudiante (E) o docente (D)? ");
                    String tipo = scanner.nextLine();
                    agregarACurso(tipo);
                    scanner1.close();
                    break;
                case 2:
                    asignarEstudiante();
                    break;
                case 3:
                    asignarDocenteACurso();
                    break;
                case 4:
                    pagarDocente();
                    break;
                case 5:
                    mostrarPagos();
                    break;
                case 6:
                    System.out.println("Saliendo del programa...");
                    go = false;
                    break;
                default:
                    System.out.println("La opción seleccionada no es válida.");

            }
        }
    }

    private void agregarACurso(String tipo) {
        Scanner scanner = new Scanner(System.in);

        if (tipo.equalsIgnoreCase("E")) {
            System.out.println("Ingrese el nombre del nuevo estudiante:");
            String nombreEstudiante = scanner.nextLine();

            System.out.println("Ingrese el nombre del nuevo curso:");
            String cursoEstudiante = scanner.nextLine();

            System.out.println("Ingrese la nota del estudiante (entre 0 y 100):");
            double notaEstudiante = Double.parseDouble(scanner.nextLine());

            System.out.println("Ingrese la sección del estudiante (entre 100 y 999):");
            int seccionEstudiante = Integer.parseInt(scanner.nextLine());

            // Agregar estudiante
            Sheet sheet = getSheetByName("ExcelFactory");
            Row newRow = sheet.createRow(sheet.getLastRowNum() + 1);
            newRow.createCell(0).setCellValue(nombreEstudiante);
            newRow.createCell(1).setCellValue(cursoEstudiante);
            newRow.createCell(2).setCellValue(notaEstudiante);
            newRow.createCell(3).setCellValue(seccionEstudiante);

            System.out.println("Estudiante agregado exitosamente.");
        } else if (tipo.equalsIgnoreCase("D")) {
            System.out.println("Ingrese el nombre del nuevo docente:");
            String nombreDocente = scanner.nextLine();

            System.out.println("Ingrese el nombre del nuevo curso:");
            String cursoDocente = scanner.nextLine();

            System.out.println("Ingrese la sección del docente (entre 100 y 999):");
            int seccionDocente = Integer.parseInt(scanner.nextLine());

            // Agregar nuevo docente
            Sheet sheet = getSheetByName("ExcelFactory");
            Row newRow = sheet.createRow(sheet.getLastRowNum() + 1);
            newRow.createCell(4).setCellValue(nombreDocente);
            newRow.createCell(5).setCellValue(cursoDocente);
            newRow.createCell(6).setCellValue(seccionDocente);

            System.out.println("Docente agregado exitosamente.");
        } else {
            System.out.println("La opción que seleccionó no es válida.");
        }

    }

    private void asignarEstudiante() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Ingrese el nombre del estudiante al que desea asignar un curso:");
        String nombreEstudiante = scanner.nextLine();

        System.out.println("Ingrese el nombre del curso:");
        String curso = scanner.nextLine();

        System.out.println("Ingrese la nota del estudiante (entre 0 y 100):");
        double nota = Double.parseDouble(scanner.nextLine());

        System.out.println("Ingrese la sección (entre 100 y 999):");
        int seccion = Integer.parseInt(scanner.nextLine());

        Sheet sheet = getSheetByName("ExcelFactory");
        Row newRow = sheet.createRow(sheet.getLastRowNum() + 1);
        newRow.createCell(0).setCellValue(nombreEstudiante);
        newRow.createCell(1).setCellValue(curso);
        newRow.createCell(2).setCellValue(nota);
        newRow.createCell(3).setCellValue(seccion);

        System.out.println("Estudiante asignado al curso exitosamente.");
    }

    private void asignarDocenteACurso() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Ingrese el nombre del docente al que desea asignar un curso:");
        String nombreDocente = scanner.nextLine();

        System.out.println("Ingrese el nombre del curso:");
        String curso = scanner.nextLine();

        System.out.println("Ingrese la sección (entre 100 y 999):");
        int seccion = Integer.parseInt(scanner.nextLine());

        // Agregar información
        Sheet sheet = getSheetByName("ExcelFactory");
        Row newRow = sheet.createRow(sheet.getLastRowNum() + 1);
        newRow.createCell(4).setCellValue(nombreDocente);
        newRow.createCell(5).setCellValue(curso);
        newRow.createCell(6).setCellValue(seccion);

        System.out.println("Docente asignado al curso exitosamente.");
    }

    private void pagarDocente() {
        Scanner scanner = new Scanner(System.in);
    
        System.out.println("¿Desea pagar a un catedrático? (Y/N): ");
        String respuesta = scanner.nextLine();
    
        if (respuesta.equalsIgnoreCase("Y")) {
            System.out.println("Ingrese el nombre del catedrático al que desea realizar el pago:");
            String nombreCatedratico = scanner.nextLine();
    
            Random rand = new Random();
            double pagoCatedratico = 1000 + (5000 - 1000) * rand.nextDouble();
    
            System.out.println("Se le debe pagar a " + nombreCatedratico + " la cantidad de " + pagoCatedratico);
            System.out.println("¿Quiere realizar el pago? (Y/N): ");
            respuesta = scanner.nextLine();
    
            if (respuesta.equalsIgnoreCase("Y")) {
+               Sheet sheet = getSheetByName("ExcelFactory");
                Row newRow = sheet.createRow(sheet.getLastRowNum() + 1);
                newRow.createCell(7).setCellValue(nombreCatedratico);
                newRow.createCell(8).setCellValue(pagoCatedratico); 
                newRow.createCell(9).setCellValue(LocalDate.now().toString());  
    
                System.out.println("Pago realizado con éxito.");
            } else if (respuesta.equalsIgnoreCase("N")) {
                System.out.println("No se ha realizado el pago.");
            } else {
                System.out.println("Respuesta no válida.");
            }
        }
    }

    private void mostrarPagos() {
        Sheet sheet = getSheetByName("ExcelFactory");

        System.out.println("Listado de Pagos:");
        for (Row row : sheet) {
            Cell nombrePagoCell = row.getCell(7);
            Cell cantidadCell = row.getCell(8);
            Cell fechaCell = row.getCell(9);

            if (nombrePagoCell != null && cantidadCell != null && fechaCell != null) {
                String nombrePago = nombrePagoCell.getStringCellValue();
                double cantidad = cantidadCell.getNumericCellValue();
                String fecha = fechaCell.getStringCellValue();

                System.out.println("Nombre: " + nombrePago + ", Cantidad: " + cantidad + ", Fecha: " + fecha);
            }
        }
    }

}
