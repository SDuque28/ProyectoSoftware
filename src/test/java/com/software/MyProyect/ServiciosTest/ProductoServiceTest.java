package com.software.MyProyect.ServiciosTest;

import com.software.MyProyect.modelos.Productos;
import com.software.MyProyect.servicios.servicioProducto;
import com.software.MyProyect.repositorios.repositorioProducto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ProductoServiceTest {
    private repositorioProducto productoRepository;
    private servicioProducto productoService;

    @BeforeEach
    public void setup() {
        productoRepository = mock(repositorioProducto.class);
        productoService = new servicioProducto(productoRepository);
    }

    @Test
    public void testCrearProducto() {
        Productos producto = new Productos("001", "1", "Laptop", 1500.0, "Alta gama", "1", "pieza", "Electrodomésticos", 10);
        when(productoRepository.save(producto)).thenReturn(producto);

        Productos resultado = productoService.saveProducto(producto);
        assertNotNull(resultado);
        assertEquals("Laptop", resultado.getNombre());
    }

    @Test
    public void testModificarProducto() {
        Productos productoExistente = new Productos("001", "1", "Laptop", 1500.0, "Alta gama", "1", "pieza", "Electrodomésticos", 10);
        when(productoRepository.findById("1")).thenReturn(Optional.of(productoExistente));

        productoExistente.setPrecioVenta(1100.0);
        productoService.updateProducto("1", productoExistente);
        Productos resultado = productoService.getProductoById("1");

        assertEquals(1100.0, resultado.getPrecioVenta());
        verify(productoRepository, times(1)).save(productoExistente);
    }

    @Test
    public void testEliminarProducto() {
        when(productoRepository.existsById("1")).thenReturn(true);

        boolean resultado = productoService.deleteProducto("1");

        assertTrue(resultado);
        verify(productoRepository, times(1)).deleteById("1");
    }

    @Test
    public void testBuscarProductosPorNombre() {
        List<Productos> productosMock = Arrays.asList(
                new Productos("001", "1", "Laptop", 1500.0, "Alta gama", "1", "pieza", "Electrodomésticos", 10),
                new Productos("002", "2", "Lámpara", 100.0, "LED", "1", "pieza", "Hogar", 20)
        );

        when(productoRepository.findByNombreContaining("Lap")).thenReturn(productosMock);

        List<Productos> resultado = productoService.buscarProductosPorNombre("Lap");
        assertNotNull(resultado);
        assertEquals(2, resultado.size());
        assertEquals("Laptop", resultado.get(0).getNombre());
    }

    @Test
    public void testBuscarProductosPorCodigo() {
        List<Productos> productosMock = Collections.singletonList(
                new Productos("001", "1", "Laptop", 1500.0, "Alta gama", "1", "pieza", "Electrodomésticos", 10)
        );

        when(productoRepository.findByCodigo("001")).thenReturn(productosMock);

        List<Productos> resultado = productoService.buscarProductosPorCodigo("001");
        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        assertEquals("Laptop", resultado.get(0).getNombre());
    }

    @Test
    public void testBuscarProductosPorCategoria() {
        List<Productos> productosMock = Arrays.asList(
                new Productos("003", "3", "Nevera", 3000.0, "Refrigerador", "1", "pieza", "Electrodomésticos", 5),
                new Productos("004", "4", "Microondas", 800.0, "Cocina", "1", "pieza", "Electrodomésticos", 8)
        );

        when(productoRepository.findByCategoriaId("Electrodomésticos")).thenReturn(productosMock);

        List<Productos> resultado = productoService.buscarProductosPorCategoria("Electrodomésticos");
        assertNotNull(resultado);
        assertEquals(2, resultado.size());
        assertEquals("Nevera", resultado.get(0).getNombre());
    }

    @Test
    public void testAgregarStock_ProductoExistente() {
        Productos productoExistente = new Productos("001", "1", "Laptop", 1500.0, "Alta gama", "1", "pieza", "Electrodomésticos", 10);

        when(productoRepository.findById("1")).thenReturn(Optional.of(productoExistente));
        when(productoRepository.save(any(Productos.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Productos resultado = productoService.agregarStock("1", 5);

        assertNotNull(resultado);
        assertEquals(15, resultado.getStock());
        verify(productoRepository, times(1)).save(productoExistente);
    }

    @Test
    public void testAgregarStock_ProductoNoExistente() {
        when(productoRepository.findById("999")).thenReturn(Optional.empty());

        Exception exception = assertThrows(IllegalArgumentException.class, () -> productoService.agregarStock("999", 5));

        assertEquals("Producto con ID 999 no encontrado.", exception.getMessage());
        verify(productoRepository, times(0)).save(any(Productos.class));
    }
}
