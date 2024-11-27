package com.software.MyProyect.servicios;

import com.software.MyProyect.utils.ExportadorInforme;
import java.io.ByteArrayOutputStream;
import java.io.IOException; 
import java.util.List;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import com.software.MyProyect.modelos.Factura;

@Service
public class ExportadorExcelService implements ExportadorInforme {

    @Override
    public byte[] exportar(List<Factura> facturas) {
        try (Workbook workbook = new XSSFWorkbook();
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
             
            Sheet sheet = workbook.createSheet("Informe de Ventas");
            Row headerRow = sheet.createRow(0);
            headerRow.createCell(0).setCellValue("Factura ID");
            headerRow.createCell(1).setCellValue("ID Cliente");
            headerRow.createCell(2).setCellValue("Subtotal");
            headerRow.createCell(3).setCellValue("Total Impuestos");
            headerRow.createCell(4).setCellValue("Total");
            headerRow.createCell(5).setCellValue("Estado");

            int rowNum = 1;
            for (Factura factura : facturas) {
                Row row = sheet.createRow(rowNum++);
                row.createCell(0).setCellValue(factura.getId());
                row.createCell(1).setCellValue(factura.getIdCliente());
                row.createCell(2).setCellValue(factura.getSubtotal());
                row.createCell(3).setCellValue(factura.getTotalImpuestos());
                row.createCell(4).setCellValue(factura.getTotal());
                row.createCell(5).setCellValue(factura.getEstado());
            }

            workbook.write(outputStream);
            return outputStream.toByteArray();
        } catch (IOException e) {
            throw new RuntimeException("Error al exportar informe en Excel", e);
        }
    }
}