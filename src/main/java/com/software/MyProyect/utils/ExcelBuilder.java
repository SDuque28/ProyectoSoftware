package com.software.MyProyect.utils;

import java.util.List;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelBuilder {

    private Workbook workbook;
    private Sheet sheet;

    public ExcelBuilder(String sheetName) {
        this.workbook = new XSSFWorkbook();
        this.sheet = workbook.createSheet(sheetName);
    }

    public ExcelBuilder agregarEncabezado(String[] encabezado) {
        Row headerRow = sheet.createRow(0);
        for (int i = 0; i < encabezado.length; i++) {
            headerRow.createCell(i).setCellValue(encabezado[i]);
        }
        return this;
    }

    public ExcelBuilder agregarFila(List<Object> datos, int rowNum) {
        Row row = sheet.createRow(rowNum);
        for (int i = 0; i < datos.size(); i++) {
            row.createCell(i).setCellValue(datos.get(i).toString());
        }
        return this;
    }

    public Workbook build() {
        return workbook;
    }
}
