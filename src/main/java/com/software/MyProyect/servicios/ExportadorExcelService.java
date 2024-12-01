package com.software.MyProyect.servicios;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.software.MyProyect.modelos.Factura;
import com.software.MyProyect.utils.ExcelBuilder;

@Service
public class ExportadorExcelService {

    public byte[] exportarExcel(List<Factura> facturas) {
        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
             
            ExcelBuilder builder = new ExcelBuilder("Informe de Ventas");
            String[] encabezado = {"Factura ID", "ID Cliente", "Subtotal", "Total Impuestos", "Total", "Estado"};
            builder.agregarEncabezado(encabezado);
            
            int rowNum = 1;
            for (Factura factura : facturas) {
                List<Object> datos = new ArrayList<>();
                datos.add(factura.getId());
                datos.add(factura.getIdCliente());
                datos.add(factura.getSubtotal());
                datos.add(factura.getTotalImpuestos());
                datos.add(factura.getTotal());
                datos.add(factura.getEstado());
                builder.agregarFila(datos, rowNum++);
            }

            builder.build().write(outputStream);

            return outputStream.toByteArray();
        } catch (IOException e) {
            throw new RuntimeException("Error al exportar informe en Excel", e);
        }
    }
}