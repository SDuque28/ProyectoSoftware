package com.software.MyProyect.controladores;
import com.software.MyProyect.servicios.servicioCategoria;
import com.software.MyProyect.modelos.Categorias;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categorias")
public class controladorCategoria {
    private final servicioCategoria categoriaService;

    @Autowired
    public controladorCategoria(servicioCategoria categoriaService) {
        this.categoriaService = categoriaService;
    }

    @PostMapping("/add")
    public Categorias addCategoria(@RequestBody Categorias categoria) {
        return categoriaService.saveCategoria(categoria);
    }

    @GetMapping("/all")
    public List<Categorias> getAllCategorias() {
        return categoriaService.getAllCategorias();
    }
}
