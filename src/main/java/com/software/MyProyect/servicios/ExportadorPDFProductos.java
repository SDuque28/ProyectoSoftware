package com.software.MyProyect.servicios;

import com.software.MyProyect.utils.ExportadorInformeProductos;
import com.itextpdf.text.pdf.PdfPTable;
import com.software.MyProyect.modelos.Factura;
import com.software.MyProyect.modelos.ProductoFactura;
import com.software.MyProyect.utils.pdfBuilder;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Service;

@Service
public class ExportadorPDFProductos implements ExportadorInformeProductos {

    @Override
    public byte[] exportarInformeTop10(List<Map<String, Object>> informe) {
        pdfBuilder pdfBuilder = new pdfBuilder()
                .iniciarDocumento()
                .agregarTitulo("Informe: Top 10 Productos Más Vendidos");

        PdfPTable tablaProductos = new PdfPTable(3);
        tablaProductos.addCell("Producto");
        tablaProductos.addCell("Cantidad Vendida");
        tablaProductos.addCell("Facturas Asociadas");

        for (Map<String, Object> producto : informe) {
            tablaProductos.addCell((String) producto.get("producto")); // Nombre del producto
            tablaProductos.addCell(String.valueOf(producto.get("cantidad"))); // Cantidad vendida
            tablaProductos.addCell((String) producto.get("facturas")); // Facturas asociadas
        }

        pdfBuilder.agregarTexto("Lista de los productos más vendidos durante el mes.");
        pdfBuilder.agregarTabla(tablaProductos);

        int totalProductosVendidos = informe.stream()
                .mapToInt(producto -> (int) producto.get("cantidad"))
                .sum();
        pdfBuilder.agregarTexto("Total de Productos Vendidos (Top 10): " + totalProductosVendidos);

        return pdfBuilder.construir();
    }
}
