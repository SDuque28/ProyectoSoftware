package com.software.MyProyect.ModelosTest;

import com.software.MyProyect.modelos.Impuestos;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ImpuestoTest {

    @Test
    void testImpuestoCreacion() {
        Impuestos impuesto = new Impuestos("1", "IVA", 0.19);

        assertNotNull(impuesto);
        assertEquals("1", impuesto.getId());
        assertEquals("IVA", impuesto.getNombre());
        assertEquals(0.19, impuesto.getPorcentaje());
    }

    @Test
    void testModificarImpuesto() {
        Impuestos impuesto = new Impuestos("1", "IVA", 0.19);

        impuesto.setPorcentaje(0.16);
        impuesto.setNombre("Impuesto Reducido");

        assertEquals("Impuesto Reducido", impuesto.getNombre());
        assertEquals(0.16, impuesto.getPorcentaje());
    }
}
