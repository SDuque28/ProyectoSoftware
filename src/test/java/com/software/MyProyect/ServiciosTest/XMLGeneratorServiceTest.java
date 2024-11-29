package com.software.MyProyect.ServiciosTest;

import com.software.MyProyect.modelos.Factura;
import com.software.MyProyect.repositorios.repositorioFactura;
import com.software.MyProyect.servicios.XMLGeneratorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class XMLGeneratorServiceTest {

    @Mock
    private repositorioFactura facturaRepository;

    @InjectMocks
    private XMLGeneratorService xmlGeneratorService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    // Tests para generarXMLFactura
    @Test
    void testGenerarXMLFactura_NoEsNulo() throws Exception {
        // Arrange
        Factura factura = new Factura(
                "1", "F-123", LocalDate.of(2024, 11, 11),
                4500.0, 500.0, 5000.0,
                "Pagada", "Cliente-1", "MetodoPago-1"
        );
        when(facturaRepository.findById("1")).thenReturn(Optional.of(factura));

        // Act
        String xmlResult = xmlGeneratorService.generarXMLFactura("1");

        // Assert
        assertNotNull(xmlResult);
    }

    @Test
    void testGenerarXMLFactura_ContieneEtiquetaFactura() throws Exception {
        // Arrange
        Factura factura = new Factura(
                "1", "F-123", LocalDate.of(2024, 11, 11),
                4500.0, 500.0, 5000.0,
                "Pagada", "Cliente-1", "MetodoPago-1"
        );
        when(facturaRepository.findById("1")).thenReturn(Optional.of(factura));

        // Act
        String xmlResult = xmlGeneratorService.generarXMLFactura("1");

        // Assert
        assertTrue(xmlResult.contains("<Factura>"));
    }

    @Test
    void testGenerarXMLFactura_ContieneIdFactura() throws Exception {
        // Arrange
        Factura factura = new Factura(
                "1", "F-123", LocalDate.of(2024, 11, 11),
                4500.0, 500.0, 5000.0,
                "Pagada", "Cliente-1", "MetodoPago-1"
        );
        when(facturaRepository.findById("1")).thenReturn(Optional.of(factura));

        // Act
        String xmlResult = xmlGeneratorService.generarXMLFactura("1");

        // Assert
        assertTrue(xmlResult.contains("<id>1</id>"));
    }

    @Test
    void testGenerarXMLFactura_ContieneCodigo() throws Exception {
        // Arrange
        Factura factura = new Factura(
                "1", "F-123", LocalDate.of(2024, 11, 11),
                4500.0, 500.0, 5000.0,
                "Pagada", "Cliente-1", "MetodoPago-1"
        );
        when(facturaRepository.findById("1")).thenReturn(Optional.of(factura));

        // Act
        String xmlResult = xmlGeneratorService.generarXMLFactura("1");

        // Assert
        assertTrue(xmlResult.contains("<codigo>F-123</codigo>"));
    }

    @Test
    void testGenerarXMLFactura_FacturaNoEncontrada() {
        // Arrange
        when(facturaRepository.findById("2")).thenReturn(Optional.empty());

        // Act & Assert
        Exception exception = assertThrows(Exception.class, () -> xmlGeneratorService.generarXMLFactura("2"));
        assertEquals("Factura no encontrada", exception.getMessage());
    }

    @Test
    void testObtenerDatosDesdeXML_InvalidXML() {
        // Arrange
        String invalidXml = "<Factura><id>1</id><codigo>F-123</codigo>";

        // Act & Assert
        assertThrows(Exception.class, () -> xmlGeneratorService.obtenerDatosDesdeXML(invalidXml));
    }
}


