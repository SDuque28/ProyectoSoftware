package com.software.MyProyect.servicios;
import com.software.MyProyect.modelos.Productos;
import com.software.MyProyect.repositorios.repositorioProducto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class servicioProducto {
    private final repositorioProducto productosRepository;

    @Autowired
    public servicioProducto(repositorioProducto productosRepository) {
        this.productosRepository = productosRepository;
    }

    public Productos saveProducto(Productos producto) {
        return productosRepository.save(producto);
    }

    public List<Productos> getAllProductos() {
        return productosRepository.findAll();
    }
    
    public Productos getProductoById(String id) {
        Optional<Productos> producto = productosRepository.findById(id);
        if (producto.isPresent()) {
            return producto.get();
        } else {
            throw new IllegalArgumentException("Producto con ID " + id + " no encontrado.");
        }
    }
    
    public Productos updateProducto(String id, Productos productoActualizado) {
        Productos productoExistente = productosRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Prodcuto con ID " + id + " no encontrado."));
        
        // Actualizar los campos del cliente
        productoExistente.setCategoriaId(productoActualizado.getCategoriaId());
        productoExistente.setDescripcion(productoActualizado.getDescripcion());
        productoExistente.setImpuestoId(productoActualizado.getImpuestoId());
        productoExistente.setMedida(productoActualizado.getMedida());
        productoExistente.setPrecioVenta(productoActualizado.getPrecioVenta());

        return productosRepository.save(productoExistente);
    }
    
    public void deleteProducto(String id) {
        if (!productosRepository.existsById(id)) {
            throw new IllegalArgumentException("Producto con ID " + id + " no encontrado.");
        }
        
        productosRepository.deleteById(id);
    }
}
