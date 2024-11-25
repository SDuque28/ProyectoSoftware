package com.software.MyProyect.controladores;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.software.MyProyect.servicios.servicioProducto;
import com.software.MyProyect.modelos.Productos;

import java.util.List;

@RestController
@RequestMapping("/productos")
public class controladorProducto {
    private final servicioProducto productosService;

    @Autowired
    public controladorProducto(servicioProducto productosService) {
        this.productosService = productosService;
    }

    // Endpoint to add a new product
    @PostMapping("/add")
    public Productos addProducto(@RequestBody Productos producto) {
        return productosService.saveProducto(producto);
    }

    // Endpoint to get all products
    @GetMapping("/all")
    public List<Productos> getAllProductos() {
        return productosService.getAllProductos();
    }

    @GetMapping("/{id}")
    public Productos getProductoById(@PathVariable String id) {
        return productosService.getProductoById(id);
    }

    @PutMapping("/{id}")
    public Productos updateProducto(@PathVariable String id, @RequestBody Productos producto) {
        return productosService.updateProducto(id, producto);
    }

    @DeleteMapping("/{id}")
    public void deleteProductos(@PathVariable String id) {
        productosService.deleteProducto(id);
    }

    @GetMapping("/categoria/{categoria}")
    public List<Productos> obtenerProductosPorCategoria(@PathVariable String categoria) {
        return productosService.obtenerProductosPorCategoria(categoria);
    }

    @GetMapping("/buscar")
    public List<Productos> buscarPorCualquierCriterio(@RequestParam String keyword) {
        return productosService.buscarPorCualquierCriterio(keyword);
    }
}
