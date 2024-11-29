package com.software.MyProyect.ModelosTest;

import com.software.MyProyect.modelos.Productos;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProductoTest {

    @Test
    void testProductoCreacion() {
        Productos producto = new Productos("001", "1", "Laptop", 1500.0, "Alta gama", "1", "pieza", "Electrodomésticos", 10);

        assertNotNull(producto);
        assertEquals("001", producto.getId());
        assertEquals("Laptop", producto.getNombre());
        assertEquals(1500.0, producto.getPrecioVenta());
        assertEquals(10, producto.getStock());
    }

    @Test
    void testModificarStockProducto() {
        Productos producto = new Productos("001", "1", "Laptop", 1500.0, "Alta gama", "1", "pieza", "Electrodomésticos", 10);

        producto.setStock(20);
        assertEquals(20, producto.getStock());
    }

    @Test
    void testModificarDescripcionProducto() {
        Productos producto = new Productos("001", "1", "Laptop", 1500.0, "Alta gama", "1", "pieza", "Electrodomésticos", 10);

        producto.setDescripcion("Gama media");
        assertEquals("Gama media", producto.getDescripcion());
    }
}
