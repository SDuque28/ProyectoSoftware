package com.software.MyProyect.servicios;
import com.software.MyProyect.modelos.Clientes;
import com.software.MyProyect.repositorios.repositorioCliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

import java.util.List;

@Service
public class servicioCliente {
    private final repositorioCliente clientesRepository;

    @Autowired
    public servicioCliente(repositorioCliente clientesRepository) {
        this.clientesRepository = clientesRepository;
    }

    public Clientes saveCliente(Clientes cliente) {
        return clientesRepository.save(cliente);
    }

    public List<Clientes> getAllClientes() {
        return clientesRepository.findAll();
    }

    public Optional<Clientes> getClienteById(String id) {
        return clientesRepository.findById(id);
    }

    public Clientes updateCliente(String id, Clientes cliente) {
        cliente.setId(id);
        return clientesRepository.save(cliente);
    }

    public void deleteCliente(String id) {
        clientesRepository.deleteById(id);
    }
}
