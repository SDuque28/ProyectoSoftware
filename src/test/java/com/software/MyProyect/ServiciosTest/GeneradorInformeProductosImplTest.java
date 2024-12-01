package com.software.MyProyect.ServiciosTest;

import com.software.MyProyect.modelos.Factura;
import com.software.MyProyect.modelos.ProductoFactura;
import com.software.MyProyect.modelos.Productos;
import com.software.MyProyect.servicios.GeneradorInformeProductosImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class GeneradorInformeProductosImplTest {
    private List<Factura> facturas;

    @BeforeEach
    void setup() {
        facturas = new ArrayList<>();

        // Crear productos simulados
        Productos producto1 = new Productos("001", "1", "Laptop", 1500.0, "Alta gama", "1", "pieza", "Electrodomésticos", 10);
        Productos producto2 = new Productos("002", "2", "Smartphone", 800.0, "Alta gama", "1", "pieza", "Electrodomésticos", 20);
        Productos producto3 = new Productos("003", "3", "Audífonos", 50.0, "Accesorios", "1", "pieza", "Electrodomésticos", 50);

        // Crear facturas simuladas
        Factura factura1 = new Factura("1", "001", LocalDate.parse("2024-11-10"), 1500.0, 150.0, 1650.0, "pagada", "1", "1");
        factura1.getProductosVendidos().add(new ProductoFactura(producto1, 5));
        factura1.getProductosVendidos().add(new ProductoFactura(producto2, 3));

        Factura factura2 = new Factura("2", "002", LocalDate.parse("2024-11-11"), 900.0, 90.0, 990.0, "pendiente", "2", "2");
        factura2.getProductosVendidos().add(new ProductoFactura(producto2, 2));
        factura2.getProductosVendidos().add(new ProductoFactura(producto3, 10));

        Factura factura3 = new Factura("3", "003", LocalDate.parse("2024-11-12"), 500.0, 50.0, 550.0, "pagada", "3", "3");
        factura3.getProductosVendidos().add(new ProductoFactura(producto3, 15));

        facturas.add(factura1);
        facturas.add(factura2);
        facturas.add(factura3);
    }

    @Test
    void testGenerarDatosTop10_NotNull() {
        GeneradorInformeProductosImpl generador = new GeneradorInformeProductosImpl(facturas);

        List<Map<String, Object>> resultado = generador.generarDatosTop10();

        assertNotNull(resultado, "El resultado no debería ser nulo.");
    }

    @Test
    void testGenerarDatosTop10_Size() {
        GeneradorInformeProductosImpl generador = new GeneradorInformeProductosImpl(facturas);

        List<Map<String, Object>> resultado = generador.generarDatosTop10();

        assertEquals(2, resultado.size(), "El tamaño del resultado debería ser 2.");
    }

    @Test
    void testGenerarDatosTop10_PrimerProducto() {
        GeneradorInformeProductosImpl generador = new GeneradorInformeProductosImpl(facturas);

        List<Map<String, Object>> resultado = generador.generarDatosTop10();

        Map<String, Object> primerProducto = resultado.get(0);

        assertEquals("Accesorios", primerProducto.get("producto"));
        assertEquals(25, primerProducto.get("cantidad"), "La cantidad del primer producto debería ser 25.");
        assertEquals("2, 3", primerProducto.get("facturas"), "Las facturas asociadas deberían ser '2, 3'.");
    }

    @Test
    void testGenerarDatosTop10_SegundoProducto() {
        GeneradorInformeProductosImpl generador = new GeneradorInformeProductosImpl(facturas);

        List<Map<String, Object>> resultado = generador.generarDatosTop10();

        Map<String, Object> tercerProducto = resultado.get(1);

        assertEquals("Alta gama", tercerProducto.get("producto"));
        assertEquals(10, tercerProducto.get("cantidad"));
    }

    @Test
    void testGenerarDatosTop10_SinFacturas() {
        GeneradorInformeProductosImpl generador = new GeneradorInformeProductosImpl(new ArrayList<>());

        List<Map<String, Object>> resultado = generador.generarDatosTop10();

        assertNotNull(resultado, "El resultado no debería ser nulo incluso sin facturas.");
        assertTrue(resultado.isEmpty(), "El resultado debería estar vacío cuando no hay facturas.");
    }
}
