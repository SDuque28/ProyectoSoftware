package com.software.MyProyect.controladores;
import com.software.MyProyect.modelos.DetalleFactura;
import com.software.MyProyect.servicios.servicioDetalle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/detalleFacturas")
public class controladorDetalle {
    private final servicioDetalle detalleFacturaService;

    @Autowired
    public controladorDetalle(servicioDetalle detalleFacturaService) {
        this.detalleFacturaService = detalleFacturaService;
    }

    // Endpoint to add a new DetalleFactura
    @PostMapping("/add")
    public DetalleFactura addDetalleFactura(@RequestBody DetalleFactura detalleFactura) {
        return detalleFacturaService.saveDetalleFactura(detalleFactura);
    }

    // Endpoint to get all DetalleFacturas
    @GetMapping("/all")
    public List<DetalleFactura> getAllDetalleFacturas() {
        return detalleFacturaService.getAllDetalleFacturas();
    }
}
