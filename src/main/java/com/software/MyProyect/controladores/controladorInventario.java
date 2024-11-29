package com.software.MyProyect.controladores;
import com.software.MyProyect.modelos.Inventarios;
import com.software.MyProyect.servicios.servicioInventario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/inventarios")
public class controladorInventario {
    private final servicioInventario inventarioService;

    @Autowired
    public controladorInventario(servicioInventario inventarioService) {
        this.inventarioService = inventarioService;
    }

    @PostMapping("/add")
    public Inventarios addInventario(@RequestBody Inventarios inventario) {
        return inventarioService.saveInventario(inventario);
    }

    @GetMapping("/all")
    public List<Inventarios> getAllInventarios() {
        return inventarioService.getAllInventarios();
    }
}
