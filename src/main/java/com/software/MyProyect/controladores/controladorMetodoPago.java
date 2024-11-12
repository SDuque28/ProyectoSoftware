package com.software.MyProyect.controladores;
import com.software.MyProyect.modelos.MetodoPago;
import com.software.MyProyect.servicios.servicioMetodoPago;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/metodosPago")
public class controladorMetodoPago {
    private final servicioMetodoPago metodoPagoService;

    @Autowired
    public controladorMetodoPago(servicioMetodoPago metodoPagoService) {
        this.metodoPagoService = metodoPagoService;
    }

    @PostMapping("/add")
    public MetodoPago addMetodoPago(@RequestBody MetodoPago metodoPago) {
        return metodoPagoService.saveMetodoPago(metodoPago);
    }

    @GetMapping("/all")
    public List<MetodoPago> getAllMetodosPago() {
        return metodoPagoService.getAllMetodosPago();
    }
}
