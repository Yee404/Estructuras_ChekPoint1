import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.*;
import java.time.LocalDate;

public class Usuario {
    protected String nombre;
    protected Workbook workbook;
    protected String sheetName = "ExcelFactory";

    public Usuario(String nombre) throws IOException {
        this.nombre = nombre;
        FileInputStream excelFile = new FileInputStream(
                new File("ExcelFactory.csv"));
        this.workbook = new XSSFWorkbook(excelFile);
    }

    protected Sheet getSheetByName(String sheetName) {
        return workbook.getSheet(sheetName);
    }
}
