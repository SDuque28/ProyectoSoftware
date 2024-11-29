package com.software.MyProyect.ModelosTest;

import com.software.MyProyect.modelos.Clientes;
import com.software.MyProyect.servicios.servicioCliente;
import com.software.MyProyect.repositorios.repositorioCliente;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ClientesServiceTest {

    private repositorioCliente clientesRepository;
    private servicioCliente clientesService;

    @BeforeEach
    public void setup() {
        clientesRepository = mock(repositorioCliente.class);
        clientesService = new servicioCliente(clientesRepository);
    }

    @Test
    public void testCrearCliente() {
        Clientes cliente = new Clientes("1", "12345678", "Juan Perez", "Calle 123", "3001234567", "juan@mail.com", "Bogotá", "Cundinamarca");
        when(clientesRepository.save(cliente)).thenReturn(cliente);

        Clientes resultado = clientesService.saveCliente(cliente);
        assertNotNull(resultado);
        assertEquals("Juan Perez", resultado.getNombre());
    }

    @Test
    public void testModificarCliente() {
        Clientes clienteExistente = new Clientes("1", "1", "Juan Perez", "Calle 456", "3001234567", "juan@mail.com", "Bogotá", "Cundinamarca");
        when(clientesRepository.findById("1")).thenReturn(Optional.of(clienteExistente));
        clientesService.updateCliente("1",clienteExistente);
        Clientes resultado = clientesService.getClienteById("1");

        assertEquals("Calle 456", resultado.getDireccion());
        verify(clientesRepository, times(1)).save(clienteExistente);
    }

    @Test
    public void testEliminarCliente() {
        Clientes cliente = new Clientes("1", "1", "Juan Perez", "Calle 123", "3001234567", "juan@mail.com", "Bogotá", "Cundinamarca");
        when(clientesRepository.existsById("1")).thenReturn(true);
        when(clientesRepository.findById("1")).thenReturn(Optional.of(cliente));

        boolean resultado = clientesService.deleteCliente("1");

        assertTrue(resultado);
        verify(clientesRepository, times(1)).deleteById("1");
    }
}
