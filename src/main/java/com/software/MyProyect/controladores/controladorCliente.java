package com.software.MyProyect.controladores;
import com.software.MyProyect.modelos.Clientes;
import com.software.MyProyect.servicios.servicioCliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

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

    @GetMapping("/{id}")
    public Optional<Clientes> getClienteById(@PathVariable String id) {
        return clientesService.getClienteById(id);
    }

    @PutMapping("/update/{id}")
    public Clientes updateCliente(@PathVariable String id, @RequestBody Clientes cliente) {
        return clientesService.updateCliente(id, cliente);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteCliente(@PathVariable String id) {
        clientesService.deleteCliente(id);
    }

}
