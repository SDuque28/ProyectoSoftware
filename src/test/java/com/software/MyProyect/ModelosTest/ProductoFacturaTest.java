package com.software.MyProyect.ModelosTest;

import com.software.MyProyect.modelos.ProductoFactura;
import com.software.MyProyect.modelos.Productos;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProductoFacturaTest {

    @Test
    void testProductoFacturaCreacion() {
        ProductoFactura productoFactura = new ProductoFactura(new Productos("001", "1", "Laptop", 1500.0, "Alta gama", "1", "pieza", "Electrodomésticos", 10), 20);

        assertNotNull(productoFactura);
        assertEquals("001", productoFactura.getProductoId());
        assertEquals("Alta gama", productoFactura.getDescripcion());
        assertEquals(1500, productoFactura.getPrecioUnitario());
    }

    @Test
    void testModificarCantidadProductoFactura() {
        ProductoFactura productoFactura = new ProductoFactura(new Productos("001", "1", "Laptop", 1500.0, "Alta gama", "1", "pieza", "Electrodomésticos", 10), 20);

        productoFactura.setCantidad(5);
        productoFactura.setPrecioUnitario(1000.0);

        assertEquals(5, productoFactura.getCantidad());
        assertEquals(1000.0, productoFactura.getPrecioUnitario());
    }

    @Test
    void testModificarDescripcionProductoFactura() {
        ProductoFactura productoFactura = new ProductoFactura(new Productos("001", "1", "Laptop", 1500.0, "Alta gama", "1", "pieza", "Electrodomésticos", 10), 20);

        productoFactura.setDescripcion("Producto B");
        assertEquals("Producto B", productoFactura.getDescripcion());
    }
}
