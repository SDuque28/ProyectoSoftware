package com.software.MyProyect.controladores;

import com.software.MyProyect.modelos.Productos;
import com.software.MyProyect.servicios.servicioProducto;
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

    // Endpoint para agregar un nuevo producto
    @PostMapping("/add")
    public Productos addProducto(@RequestBody Productos producto) {
        return productosService.saveProducto(producto);
    }

    // Endpoint para obtener todos los productos
    @GetMapping("/all")
    public List<Productos> getAllProductos() {
        return productosService.getAllProductos();
    }

    // Endpoint para obtener un producto por ID
    @GetMapping("/{id}")
    public Productos getProductoById(@PathVariable String id) {
        return productosService.getProductoById(id);
    }

    // Endpoint para actualizar un producto
    @PutMapping("/{id}")
    public Productos updateProducto(@PathVariable String id, @RequestBody Productos producto) {
        return productosService.updateProducto(id, producto);
    }

    // Endpoint para eliminar un producto
    @DeleteMapping("/{id}")
    public void deleteProductos(@PathVariable String id) {
        productosService.deleteProducto(id);
    }
    //Endpoint para buscar producto por nombre
    @GetMapping("/buscar/nombre/{nombre}")
    public List<Productos> buscarPorNombre(@PathVariable String nombre){
        return productosService.buscarProductosPorNombre(nombre);
    }

    //EndPoint para buscar producto por codigo
    @GetMapping("/buscar/codigo/{codigo}")
    public List<Productos> buscarPorCodigo(@PathVariable String codigo){
        return productosService.buscarProductosPorCodigo(codigo);
    }

    @GetMapping("/buscar/categoria/{categoriaId}")
    public List<Productos> buscarPorCategoria(@PathVariable String categoriaId){
        return productosService.buscarProductosPorCategoria(categoriaId);
    }

    @GetMapping("/categoria/{categoria}")
    public List<Productos> obtenerProductosPorCategoria(@PathVariable String categoria) {
        return productosService.obtenerProductosPorCategoria(categoria);
    }

    @GetMapping("/buscar")
    public List<Productos> buscarPorCualquierCriterio(@RequestParam String keyword) {
        return productosService.buscarPorCualquierCriterio(keyword);
    }

    @PutMapping("/{id}/agregar-stock")
    public Productos agregarStock(@PathVariable String id, @RequestParam int cantidad){
        return productosService.agregarStock(id, cantidad);
    }

}