package com.software.MyProyect.ModelosTest;

import com.software.MyProyect.modelos.Clientes;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ClienteTest {

    @Test
    void testClienteCreacion() {
        Clientes cliente = new Clientes("1", "12345678", "Juan Perez", "Calle 123", "3001234567", "juan@mail.com", "Bogotá", "Cundinamarca");

        assertNotNull(cliente);
        assertEquals("12345678", cliente.getId());
        assertEquals("1", cliente.getNumeroDocumento());
        assertEquals("Juan Perez", cliente.getNombre());
        assertEquals("Calle 123", cliente.getDireccion());
        assertEquals("3001234567", cliente.getTelefono());
        assertEquals("juan@mail.com", cliente.getEmail());
    }

    @Test
    void testModificarCliente() {
        Clientes cliente = new Clientes("1", "12345678", "Juan Perez", "Calle 123", "3001234567", "juan@mail.com", "Bogotá", "Cundinamarca");

        cliente.setNombre("Carlos Lopez");
        cliente.setDireccion("Calle 456");

        assertEquals("Carlos Lopez", cliente.getNombre());
        assertEquals("Calle 456", cliente.getDireccion());
    }
}
