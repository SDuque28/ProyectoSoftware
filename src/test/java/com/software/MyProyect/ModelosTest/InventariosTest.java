package com.software.MyProyect.ModelosTest;

import com.software.MyProyect.modelos.Inventarios;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InventariosTest {

    @Test
    void testInventarioCreacion() {
        Inventarios inventario = new Inventarios("1", "2024-01-22", "Caja",5000,3500,"Oservacion","02");

        assertNotNull(inventario);
        assertEquals("1", inventario.getId());
        assertEquals("2024-01-22", inventario.getFecha());
        assertEquals(5000, inventario.getEntrada());
    }

    @Test
    void testModificarCantidadInventario() {
        Inventarios inventario = new Inventarios("1", "2024-01-22", "Caja",5000,3500,"Oservacion","02");

        inventario.setEntrada(150);
        assertEquals(150, inventario.getEntrada());
    }

    @Test
    void testModificarTipoMovimientoInventario() {
        Inventarios inventario = new Inventarios("1", "2024-01-22", "Caja",5000,3500,"Oservacion","02");

        inventario.setTipoMovimiento("Caja");
        assertEquals("Caja", inventario.getTipoMovimiento());
    }
}
