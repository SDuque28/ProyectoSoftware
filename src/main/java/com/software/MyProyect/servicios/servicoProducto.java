package com.software.MyProyect.servicios;
import com.software.MyProyect.modelos.Productos;
import com.software.MyProyect.repositorios.repositorioProducto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class servicoProducto {
    private final repositorioProducto productosRepository;

    @Autowired
    public servicoProducto(repositorioProducto productosRepository) {
        this.productosRepository = productosRepository;
    }

    public Productos saveProducto(Productos producto) {
        return productosRepository.save(producto);
    }

    public List<Productos> getAllProductos() {
        return productosRepository.findAll();
    }
}
