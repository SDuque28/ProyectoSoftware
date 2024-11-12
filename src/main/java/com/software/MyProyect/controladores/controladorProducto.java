package com.software.MyProyect.controladores;
import com.software.MyProyect.modelos.Productos;
import com.software.MyProyect.servicios.servicoProducto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/productos")
public class controladorProducto {
    private final servicoProducto productosService;

    @Autowired
    public controladorProducto(servicoProducto productosService) {
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
}
