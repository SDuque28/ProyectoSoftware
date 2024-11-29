package com.software.MyProyect.ModelosTest;

import com.software.MyProyect.modelos.Factura;
import com.software.MyProyect.modelos.ProductoFactura;
import com.software.MyProyect.modelos.Productos;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FacturaTest {

    @Test
    void testFacturaCreacion() {
        Factura factura = new Factura("1", "001", LocalDate.parse("2024-11-10"), 1500.0, 150.0, 1650.0, "pagada", "1", "1");

        assertNotNull(factura);
        assertEquals("1", factura.getId());
        assertEquals("001", factura.getCodigo());
        assertEquals(1500.0, factura.getSubtotal());
        assertEquals(150.0, factura.getTotalImpuestos());
        assertEquals(1650.0, factura.getTotal());
        assertEquals("pagada", factura.getEstado());
    }

    @Test
    void testAgregarProductosVendidos() {
        Factura factura = new Factura("1", "001", LocalDate.parse("2024-11-10"), 1500.0, 150.0, 1650.0, "pagada", "1", "1");
        List<ProductoFactura> productos = new ArrayList<>();
        ProductoFactura producto1 = new ProductoFactura(new Productos(), 10);

        productos.add(producto1);
        factura.setProductosVendidos(productos);

        assertEquals(1, factura.getProductosVendidos().size());
    }

    @Test
    void testModificarEstadoFactura() {
        Factura factura = new Factura("1", "001", LocalDate.parse("2024-11-10"), 1500.0, 150.0, 1650.0, "pendiente", "1", "1");

        factura.setEstado("pagada");
        assertEquals("pagada", factura.getEstado());
    }
}
