package com.software.MyProyect.servicios;
import com.software.MyProyect.modelos.Productos;
import com.software.MyProyect.repositorios.repositorioProductos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class servicoClientes {
    private final repositorioProductos productosRepository;

    @Autowired
    public servicoClientes(repositorioProductos productosRepository) {
        this.productosRepository = productosRepository;
    }

    public Productos saveProducto(Productos producto) {
        return productosRepository.save(producto);
    }

    public List<Productos> getAllProductos() {
        return productosRepository.findAll();
    }
}
