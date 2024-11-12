package com.software.MyProyect.servicios;
import com.software.MyProyect.modelos.Clientes;
import com.software.MyProyect.repositorios.repositorioClientes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class servicioCliente {
    private final repositorioClientes clientesRepository;

    @Autowired
    public servicioCliente(repositorioClientes clientesRepository) {
        this.clientesRepository = clientesRepository;
    }

    public Clientes saveCliente(Clientes cliente) {
        return clientesRepository.save(cliente);
    }

    public List<Clientes> getAllClientes() {
        return clientesRepository.findAll();
    }
}
