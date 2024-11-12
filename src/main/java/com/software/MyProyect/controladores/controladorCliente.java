package com.software.MyProyect.controladores;
import com.software.MyProyect.modelos.Clientes;
import com.software.MyProyect.servicios.servicioCliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientes")
public class controladorCliente {
    private final servicioCliente clientesService;

    @Autowired
    public controladorCliente(servicioCliente clientesService) {
        this.clientesService = clientesService;
    }

    @PostMapping("/add")
    public Clientes addCliente(@RequestBody Clientes cliente) {
        return clientesService.saveCliente(cliente);
    }

    @GetMapping("/all")
    public List<Clientes> getAllClientes() {
        return clientesService.getAllClientes();
    }
}
