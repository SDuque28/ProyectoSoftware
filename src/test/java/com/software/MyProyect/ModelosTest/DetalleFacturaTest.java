package com.software.MyProyect.ModelosTest;

import com.software.MyProyect.modelos.DetalleFactura;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DetalleFacturaTest {

    @Test
    void testDetalleFacturaCreacion() {
        DetalleFactura detalle = new DetalleFactura("1", "02", "1", 500.0, 1000.0,4);

        assertNotNull(detalle);
        assertEquals("1", detalle.getId());
        assertEquals("02", detalle.getIdProducto());
        assertEquals(4, detalle.getCantidad());
        assertEquals(500.0, detalle.getDescuento());
        assertEquals(1000.0, detalle.getValorTotal());
    }

    @Test
    void testModificarCantidadDetalleFactura() {
        DetalleFactura detalle = new DetalleFactura("1", "Producto A", "2", 500.0, 1000.0,3);

        detalle.setCantidad(5);
        detalle.setValorTotal(2500.0);

        assertEquals(5, detalle.getCantidad());
        assertEquals(2500.0, detalle.getValorTotal());
    }

    @Test
    void testModificarIdProductoDetalleFactura() {
        DetalleFactura detalle = new DetalleFactura("1", "Producto A", "2", 500.0, 1000.0,5);

        detalle.setIdProducto("4");
        assertEquals("4", detalle.getIdProducto());
    }
}
