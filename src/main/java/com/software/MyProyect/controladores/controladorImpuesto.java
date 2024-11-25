package com.software.MyProyect.controladores;
import com.software.MyProyect.modelos.Impuestos;
import com.software.MyProyect.modelos.Productos;
import com.software.MyProyect.servicios.servicioImpuesto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/impuestos")
public class controladorImpuesto {
    private final servicioImpuesto impuestoService;

    @Autowired
    public controladorImpuesto(servicioImpuesto impuestoService) {
        this.impuestoService = impuestoService;
    }

    @PostMapping("/add")
    public Impuestos addImpuesto(@RequestBody Impuestos impuesto) {
        return impuestoService.saveImpuesto(impuesto);
    }

    @GetMapping("/all")
    public List<Impuestos> getAllImpuestos() {
        return impuestoService.getAllImpuestos();
    }

    @GetMapping("/{id}")
    public Impuestos getImpuestoById(@PathVariable String id) {
        return impuestoService.getImpuestoById(id);
    }

    @PutMapping("/{id}")
    public Impuestos updateProducto(@PathVariable String id, @RequestBody Impuestos impuesto) {
        return impuestoService.updateImpuesto(id, impuesto);
    }

    @DeleteMapping("/{id}")
    public void deleteImpuestos(@PathVariable String id) {
        impuestoService.deleteImpuesto(id);
    }
}
