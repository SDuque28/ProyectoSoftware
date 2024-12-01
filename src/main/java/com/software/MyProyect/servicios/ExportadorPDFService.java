package com.software.MyProyect.servicios;

import com.software.MyProyect.utils.ExportadorInforme;
import com.itextpdf.text.pdf.PdfPTable;
import com.software.MyProyect.modelos.Factura;
import com.software.MyProyect.modelos.ProductoFactura;
import com.software.MyProyect.utils.pdfBuilder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExportadorPDFService implements ExportadorInforme {

    @Override
    public byte[] exportar(List<Factura> facturas) {
        pdfBuilder pdfBuilder = new pdfBuilder()
                .iniciarDocumento()
                .agregarTitulo("Informe de Ventas Mensual");

        PdfPTable tablaFacturas = new PdfPTable(5);
        tablaFacturas.addCell("Factura ID");
        tablaFacturas.addCell("Cliente ID");
        tablaFacturas.addCell("Subtotal");
        tablaFacturas.addCell("Total Impuestos");
        tablaFacturas.addCell("Total");

        double totalMensual = 0.0;

        for (Factura factura : facturas) {
            tablaFacturas.addCell(factura.getId());
            tablaFacturas.addCell(factura.getIdCliente());
            tablaFacturas.addCell(String.valueOf(factura.getSubtotal()));
            tablaFacturas.addCell(String.valueOf(factura.getTotalImpuestos()));
            tablaFacturas.addCell(String.valueOf(factura.getTotal()));

            totalMensual += factura.getTotal();

            // Tabla de Productos Vendidos en la Factura
            PdfPTable tablaProductos = new PdfPTable(4);
            tablaProductos.addCell("Producto ID");
            tablaProductos.addCell("Descripción");
            tablaProductos.addCell("Cantidad");
            tablaProductos.addCell("Total");

            for (ProductoFactura producto : factura.getProductosVendidos()) {
                tablaProductos.addCell(producto.getProductoId());
                tablaProductos.addCell(producto.getDescripcion());
                tablaProductos.addCell(String.valueOf(producto.getCantidad()));
                tablaProductos.addCell(String.format("%.2f", producto.getTotal()));
            }

            pdfBuilder.agregarTexto("Productos vendidos para la Factura ID: " + factura.getId());
            pdfBuilder.agregarTabla(tablaProductos);
        }

        pdfBuilder.agregarTexto("Resumen de Facturas");
        pdfBuilder.agregarTabla(tablaFacturas);

        pdfBuilder.agregarTexto("Total General Facturado del Mes: " + String.format("%.2f", totalMensual));

        return pdfBuilder.construir();
    }
}
