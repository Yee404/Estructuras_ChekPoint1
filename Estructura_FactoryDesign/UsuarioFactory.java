import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.*;
import java.time.LocalDate;

public class UsuarioFactory {
    public Usuario getUsuario(String nombre, String excelFilePath) throws IOException {
        Sheet sheet = new WorkbookFactory().create(new FileInputStream(excelFilePath)).getSheet("Usuarios");
        for (Row row : sheet) {
            if (row.getCell(0).getStringCellValue().equals(nombre)) {
                String rol = row.getCell(1).getStringCellValue();
                switch (rol) {
                    case "Estudiante":
                        return new Estudiante(nombre, excelFilePath);
                    case "Docente":
                        return new Docente(nombre, excelFilePath);
                    case "PersonalAdministrativo":
                        return new PersonalAdministrativo(nombre, excelFilePath);
                    case "AuditorExterno":
                        return new AuditorExterno(nombre, excelFilePath);
                    default:
                        return null;
                }
            }
        }
        return null;
    }
}