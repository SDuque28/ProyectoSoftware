package com.software.MyProyect.modelos;

import java.util.List;
import java.util.stream.Collectors;

public class CategoriaManager {
    public List<Productos> filtrarPorCategoria(List<Productos> productos, String categoria) {
        return productos.stream()
                .filter(producto -> categoria.equalsIgnoreCase(producto.getCategoriaId()))
                .collect(Collectors.toList());
    }

    public List<String> listarCategoriasDisponibles(List<Productos> productos) {
        return productos.stream()
                .map(Productos::getCategoriaId)
                .distinct()
                .collect(Collectors.toList());
    }
}
