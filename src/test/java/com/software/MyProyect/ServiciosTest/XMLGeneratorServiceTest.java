package com.software.MyProyect.ServiciosTest;

import com.software.MyProyect.modelos.Factura;
import com.software.MyProyect.servicios.XMLGeneratorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class XMLGeneratorServiceTest {

    private XMLGeneratorService xmlGeneratorService;

    @BeforeEach
    void setUp() {
        xmlGeneratorService = new XMLGeneratorService();
    }

    @Test
    void testExportar_NoEsNulo() {
        // Arrange
        Factura factura1 = new Factura("1", "F-123", LocalDate.of(2024, 11, 11),
                4500.0, 500.0, 5000.0, "Pagada", "Cliente-1", "MetodoPago-1");
        Factura factura2 = new Factura("2", "F-124", LocalDate.of(2024, 11, 12),
                3000.0, 300.0, 3300.0, "Pendiente", "Cliente-2", "MetodoPago-2");

        List<Factura> facturas = List.of(factura1, factura2);

        // Act
        byte[] xmlBytes = xmlGeneratorService.exportar(facturas);

        // Assert
        assertNotNull(xmlBytes);
    }

    @Test
    void testExportar_ContieneRaizFacturas() {
        // Arrange
        Factura factura1 = new Factura("1", "F-123", LocalDate.of(2024, 11, 11),
                4500.0, 500.0, 5000.0, "Pagada", "Cliente-1", "MetodoPago-1");

        List<Factura> facturas = List.of(factura1);

        // Act
        byte[] xmlBytes = xmlGeneratorService.exportar(facturas);
        String xmlContent = new String(xmlBytes);

        // Assert
        assertTrue(xmlContent.contains("<Facturas>"));
        assertTrue(xmlContent.contains("</Facturas>"));
    }

    @Test
    void testExportar_ContieneDatosDeFactura() {
        // Arrange
        Factura factura1 = new Factura("1", "F-123", LocalDate.of(2024, 11, 11),
                4500.0, 500.0, 5000.0, "Pagada", "Cliente-1", "MetodoPago-1");

        List<Factura> facturas = List.of(factura1);

        // Act
        byte[] xmlBytes = xmlGeneratorService.exportar(facturas);
        String xmlContent = new String(xmlBytes);

        // Assert
        assertTrue(xmlContent.contains("<Factura>"));
        assertTrue(xmlContent.contains("<ID>1</ID>"));
        assertTrue(xmlContent.contains("<ClienteID>Cliente-1</ClienteID>"));
        assertTrue(xmlContent.contains("<Subtotal>4500.0</Subtotal>"));
        assertTrue(xmlContent.contains("<TotalImpuestos>500.0</TotalImpuestos>"));
        assertTrue(xmlContent.contains("<Total>5000.0</Total>"));
        assertTrue(xmlContent.contains("</Factura>"));
    }

    @Test
    void testExportar_MultiplesFacturas() {
        // Arrange
        Factura factura1 = new Factura("1", "F-123", LocalDate.of(2024, 11, 11),
                4500.0, 500.0, 5000.0, "Pagada", "Cliente-1", "MetodoPago-1");
        Factura factura2 = new Factura("2", "F-124", LocalDate.of(2024, 11, 12),
                3000.0, 300.0, 3300.0, "Pendiente", "Cliente-2", "MetodoPago-2");

        List<Factura> facturas = List.of(factura1, factura2);

        // Act
        byte[] xmlBytes = xmlGeneratorService.exportar(facturas);
        String xmlContent = new String(xmlBytes);

        // Assert
        assertTrue(xmlContent.contains("<Factura>"));
        assertTrue(xmlContent.contains("<ID>1</ID>"));
        assertTrue(xmlContent.contains("<ID>2</ID>"));
        assertTrue(xmlContent.contains("</Factura>"));
    }

    @Test
    void testExportar_ListaVacia() {
        // Arrange
        List<Factura> facturas = List.of();

        // Act
        byte[] xmlBytes = xmlGeneratorService.exportar(facturas);
        String xmlContent = new String(xmlBytes);

        // Assert
        assertFalse(xmlContent.contains("<Facturas/>"));
    }
}


