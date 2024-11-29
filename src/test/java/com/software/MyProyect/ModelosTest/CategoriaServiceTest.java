package com.software.MyProyect.ModelosTest;

import com.software.MyProyect.modelos.Categorias;
import com.software.MyProyect.repositorios.repositorioCategoria;
import com.software.MyProyect.servicios.servicioCategoria;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class CategoriaServiceTest {
    private repositorioCategoria categoriaRepository;
    private servicioCategoria categoriaService;

    @BeforeEach
    public void setup() {
        categoriaRepository = mock(repositorioCategoria.class);
        categoriaService = new servicioCategoria(categoriaRepository);
    }

    @Test
    public void testCrearCategoria() {
        Categorias categoria = new Categorias("1", "Electrodomésticos");
        when(categoriaRepository.save(categoria)).thenReturn(categoria);

        Categorias resultado = categoriaService.saveCategoria(categoria);
        assertNotNull(resultado);
        assertEquals("Electrodomésticos", resultado.getDescripcion());
    }

}
