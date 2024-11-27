package com.software.MyProyect.servicios;
import com.software.MyProyect.modelos.CategoriaManager;
import com.software.MyProyect.modelos.Productos;
import com.software.MyProyect.repositorios.repositorioProducto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class servicioProducto {
    private final repositorioProducto productosRepository;
    private final CategoriaManager categoriaManager;

    @Autowired
    public servicioProducto(repositorioProducto productosRepository) {
        this.productosRepository = productosRepository;
        this.categoriaManager = new CategoriaManager();
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

    public List<Productos> buscarProductosPorNombre(String nombre){
        return productosRepository.findByNombreContaining(nombre);
    }

    public List<Productos> buscarProductosPorCodigo(String codigo){
        return productosRepository.findByCodigo(codigo);
    }
    public List<Productos> buscarProductosPorCategoria(String categoriaId){
        return productosRepository.findByCategoriaId(categoriaId);
    }
    public Productos agregarStock(String id, int cantidad){
        if (cantidad <= 0){
            throw new IllegalArgumentException("La cantidad a agregar debe ser mayor a 0");
        }
        Productos productoExistente = productosRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Producto con ID "+ id + " no encontrado. "));

        productoExistente.setStock(productoExistente.getStock() + cantidad);
        return productosRepository.save(productoExistente);
    }

    // Método para obtener productos por categoría
    public List<Productos> obtenerProductosPorCategoria(String categoria) {
        List<Productos> productos = productosRepository.findAll();
        return categoriaManager.filtrarPorCategoria(productos, categoria);
    }
}
