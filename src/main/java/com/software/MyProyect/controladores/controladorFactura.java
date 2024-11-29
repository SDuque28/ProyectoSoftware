package com.software.MyProyect.controladores;
import com.software.MyProyect.modelos.Factura;
import com.software.MyProyect.servicios.servicioFactura;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/facturas")
public class controladorFactura {
    private final servicioFactura facturaService;

    @Autowired
    public controladorFactura(servicioFactura facturaService) {
        this.facturaService = facturaService;
    }

    @PostMapping("/add")
    public Factura addFactura(@RequestBody Factura factura) {
        return facturaService.saveFactura(factura);
    }

    @GetMapping("/all")
    public List<Factura> getAllFacturas() {
        return facturaService.getAllFacturas();
    }
}
