package com.software.MyProyect.ModelosTest;

import com.software.MyProyect.modelos.MetodoPago;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MetodoPagoTest {
    @Test
    void testMetodoDePagoCreacion() {
        MetodoPago metodo = new MetodoPago("1", "Tarjeta de Crédito","2");

        assertNotNull(metodo);
        assertEquals("1", metodo.getId());
        assertEquals("Tarjeta de Crédito", metodo.getDescripcion());
    }

    @Test
    void testModificarDescripcionMetodoDePago() {
        MetodoPago metodo = new MetodoPago("1", "Tarjeta de Crédito","2");

        metodo.setDescripcion("Transferencia Bancaria");
        assertEquals("Transferencia Bancaria", metodo.getDescripcion());
    }
}
