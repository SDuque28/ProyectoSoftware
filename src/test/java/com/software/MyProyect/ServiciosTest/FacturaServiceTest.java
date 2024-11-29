package com.software.MyProyect.ServiciosTest;

import com.software.MyProyect.modelos.Factura;
import com.software.MyProyect.servicios.servicioFactura;
import com.software.MyProyect.repositorios.repositorioFactura;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class FacturaServiceTest {
    private repositorioFactura facturaRepository;
    private servicioFactura facturaService;

    @BeforeEach
    public void setup() {
        facturaRepository = mock(repositorioFactura.class);
        facturaService = new servicioFactura(facturaRepository);
    }

    @Test
    public void testCrearFactura() {
        Factura factura = new Factura("1", "F001", LocalDate.parse("2024-01-01"), 1000.0, 190.0, 1190.0, "Pagada", "1", "1");
        when(facturaRepository.save(factura)).thenReturn(factura);

        Factura resultado = facturaService.saveFactura(factura);
        assertNotNull(resultado);
        assertEquals("F001", resultado.getCodigo());
    }
}
