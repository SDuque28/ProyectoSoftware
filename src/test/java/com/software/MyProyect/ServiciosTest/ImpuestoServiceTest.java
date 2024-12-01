package com.software.MyProyect.ServiciosTest;

import com.software.MyProyect.modelos.Impuestos;
import com.software.MyProyect.servicios.servicioImpuesto;
import com.software.MyProyect.repositorios.repositorioImpuesto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ImpuestoServiceTest {
    private repositorioImpuesto impuestoRepository;
    private servicioImpuesto impuestoService;

    @BeforeEach
    public void setup() {
        impuestoRepository = mock(repositorioImpuesto.class);
        impuestoService = new servicioImpuesto(impuestoRepository);
    }

    @Test
    public void testCrearImpuesto() {
        Impuestos impuesto = new Impuestos("1", "IVA", 19.0);
        when(impuestoRepository.save(impuesto)).thenReturn(impuesto);

        Impuestos resultado = impuestoService.saveImpuesto(impuesto);
        assertNotNull(resultado);
        assertEquals(19.0, resultado.getPorcentaje());
    }

    @Test
    public void testModificarImpuesto() {
        Impuestos impuestoExistente = new Impuestos("1", "IVA", 19.0);
        when(impuestoRepository.findById("1")).thenReturn(Optional.of(impuestoExistente));

        impuestoExistente.setPorcentaje(21.0);
        impuestoService.updateImpuesto("1", impuestoExistente);
        Impuestos resultado = impuestoService.getImpuestoById("1");

        assertEquals(21.0, resultado.getPorcentaje());
        verify(impuestoRepository, times(1)).save(impuestoExistente);
    }

    @Test
    public void testEliminarImpuesto() {
        when(impuestoRepository.existsById("1")).thenReturn(true);

        boolean resultado = impuestoService.deleteImpuesto("1");

        assertTrue(resultado);
        verify(impuestoRepository, times(1)).deleteById("1");
    }
}
